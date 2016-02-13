package com.chap2.example1;

public interface CharacterSource {

	
	
	public void nextCharacter();
	
	
	void addCharacterListener(CharacterListener listener);
	void removeCharacterListener(CharacterListener listener);

	
}
