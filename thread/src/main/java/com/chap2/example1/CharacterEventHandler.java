/**
 * 
 */
package com.chap2.example1;

import java.util.Vector;

/**
 * @author snoopy
 *
 */
public class CharacterEventHandler {

	
	private Vector<CharacterListener> listerers =new Vector<CharacterListener>();
	
	public void addCharacterListener(CharacterListener listener){
		listerers.add(listener);
	}
	public void removeCharacterListener(CharacterListener listener){
		listerers.remove(listener);
	}
	
	
	public void fireNewCharacter(CharacterSource source,int c){
		CharacterEvent en=new CharacterEvent(source, c);
		//把这个event通知所有的listener
		for(CharacterListener l:listerers){
			l.newCharacter(en);
		}
	}
}
