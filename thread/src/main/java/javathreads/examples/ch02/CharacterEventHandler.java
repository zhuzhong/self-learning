package javathreads.examples.ch02;

import java.util.*;

public class CharacterEventHandler {
    private Vector<CharacterListener> listeners = new Vector<CharacterListener>();

    public void addCharacterListener(CharacterListener cl) {
        listeners.add(cl);
    }

    public void removeCharacterListener(CharacterListener cl) {
        listeners.remove(cl);
    }

    public void fireNewCharacter(CharacterSource source, int c) {
            CharacterEvent ce = new CharacterEvent(source, c);
            /*
             * 为什么将它变为数组呢?这个也是有race-condition的,这个方法没有锁,当执行循环的时候,有可能其他线程
             * 执行了add或remove characterListener,
             * 如果不转换成的数组,这个循环有可能会失败.
             */
			CharacterListener[] cl = (CharacterListener[] )listeners.toArray(new CharacterListener[0]);
			for (int i = 0; i < cl.length; i++){
			    cl[i].newCharacter(ce);
			}
	

    }
    
    
}
