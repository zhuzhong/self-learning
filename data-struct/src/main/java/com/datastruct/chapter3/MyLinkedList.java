package com.datastruct.chapter3;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

	

	private static class Node<T>{
		public T data;
		public Node<T> prev;
		public Node<T> next;
		public Node(T data, Node<T> prev, Node<T> next) {
			super();
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	
	private int theSize;
	private int modCount=0;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	
	
	public int size(){
		return this.theSize;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	public MyLinkedList(){
		clear();
	}
	
	public void clear(){
		this.beginMarker=new Node<T>(null,null,null);
		this.endMarker=new Node<T>(null,beginMarker,null);
		this.beginMarker.next=this.endMarker;
		this.theSize=0;
		modCount++;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
