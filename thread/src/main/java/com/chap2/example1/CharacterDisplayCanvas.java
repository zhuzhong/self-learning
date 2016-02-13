package com.chap2.example1;

public class CharacterDisplayCanvas implements CharacterListener{

	@Override
	public void newCharacter(CharacterEvent event) {
	         System.out.println("得到的字母是     "+(char)event.getCharacter());
	}

}
