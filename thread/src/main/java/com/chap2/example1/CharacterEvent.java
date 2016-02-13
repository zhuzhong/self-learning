package com.chap2.example1;

public class CharacterEvent {
	private CharacterSource source;
	private int character;

	public CharacterEvent(CharacterSource source, int character) {
		this.source = source;
		this.character = character;
	}

	public CharacterSource getSource() {
		return source;
	}

	public int getCharacter() {
		return character;
	}
	
	
	
	
	
}
