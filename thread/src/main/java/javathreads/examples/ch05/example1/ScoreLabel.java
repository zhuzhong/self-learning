package javathreads.examples.ch05.example1;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import javathreads.examples.ch05.*;

public class ScoreLabel extends JLabel implements CharacterListener {
    private AtomicInteger score = new AtomicInteger(0);
    private AtomicInteger char2type = new AtomicInteger(-1);
    private AtomicReference<CharacterSource> generator = null;
    private AtomicReference<CharacterSource> typist = null;

    public ScoreLabel (CharacterSource generator, CharacterSource typist) {
        this.generator = new AtomicReference(generator);
        this.typist = new AtomicReference(typist);

        if (generator != null)
             generator.addCharacterListener(this);
        if (typist != null)
             typist.addCharacterListener(this);       
    }

    public ScoreLabel () {
        this(null, null);
    }

    public void resetGenerator(CharacterSource newGenerator) {
    	/**
    	 * 注意这个方法的算法更改逻辑
    	 */
        CharacterSource oldGenerator;

        if (newGenerator != null)
            newGenerator.addCharacterListener(this);

        oldGenerator = generator.getAndSet(newGenerator);
        if (oldGenerator != null)
            oldGenerator.removeCharacterListener(this);
    }

    public void resetTypist(CharacterSource newTypist) {
        CharacterSource oldTypist;

        if (newTypist != null)
            newTypist.addCharacterListener(this);

        oldTypist = typist.getAndSet(newTypist);
        if (oldTypist != null)
            oldTypist.removeCharacterListener(this);
    }

    public void resetScore() { 
    	/*
    	 * score.set 与char2type.set这两个方法，虽然没有以原子的方式执行完成，但是
    	却不影响整个类的安全性，因为这两个方法没有相互依赖，只需保证单个方法以原子的方式执行即可，
    	这个情况与 java并发编程中的因式分解那个例子不同，
    	 */
        score.set(0);
        char2type.set(-1);
        
        setScore();
    }

    private void setScore() {
        // This method will be explained later in chapter 7
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setText(Integer.toString(score.get()));
            }
        });
    }

    public void newCharacter(CharacterEvent ce) {
        int oldChar2type;
 
        // Previous character not typed correctly - 1 point penalty
        if (ce.source == generator.get()) {
            oldChar2type = char2type.getAndSet(ce.character); //接收随机字母

            if (oldChar2type != -1) {
                score.decrementAndGet();
                setScore();
            }
        }
        // If character is extraneous - 1 point penalty
        // If character does not match - 1 point penalty
        else if (ce.source == typist.get()) {
        	//在此有个假设：即正在使用的该变量值不会被变更且程序代码完成时也是如此
        	//所有已经被我们设定的具有特定值的变量就应当是那个值。
            while (true) {
                oldChar2type = char2type.get(); //获取上次已接收到的随机字母

                if (oldChar2type != ce.character) { //ce.character是用户输入的字母，现在作比较
                    score.decrementAndGet();
                    break;
                } else if (char2type.compareAndSet(oldChar2type, -1)) {
                    score.incrementAndGet();
                    break;
                }
            }

            setScore();
        }
    } 
}
