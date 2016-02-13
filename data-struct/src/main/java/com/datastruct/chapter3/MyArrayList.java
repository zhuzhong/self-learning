/**
 * 
 */
package com.datastruct.chapter3;

import java.util.Iterator;

/**
 * @author snoopy
 *
 */
public class MyArrayList<T> implements Iterable<T> {


	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private int theSize;
	private T[] theItems;
	
	
	private class ArrayListIterator implements Iterator<T>{

		
		private int current=0;
		@Override
		public boolean hasNext() {

			return false;
		}

		@Override
		public T next() {

			return theItems[current++];
		}

		@Override
		public void remove() {

			
		}
		
	}

}
