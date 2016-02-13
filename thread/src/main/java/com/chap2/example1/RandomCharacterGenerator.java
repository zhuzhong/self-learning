package com.chap2.example1;

import java.util.Random;

public class RandomCharacterGenerator extends Thread implements CharacterSource{

	
	private static char[]  chars="abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	Random r;
	
	CharacterEventHandler handler;
	@Override
	public void nextCharacter() {
		
		handler.fireNewCharacter(this, (int)chars[r.nextInt(chars.length)]);
	}

	
	public RandomCharacterGenerator(){
		r=new Random();
		handler=new CharacterEventHandler();
	}


	@Override
	public void addCharacterListener(CharacterListener listener) {
		handler.addCharacterListener(listener);
		
	}


	@Override
	public void removeCharacterListener(CharacterListener listener) {
		handler.removeCharacterListener(listener);
		
	}
	@Override
	public void run(){
		while(true){
			nextCharacter();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
