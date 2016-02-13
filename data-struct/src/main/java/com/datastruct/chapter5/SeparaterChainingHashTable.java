package com.datastruct.chapter5;

import java.util.LinkedList;
import java.util.List;


//分离链表法，所需要的类架构
public class SeparaterChainingHashTable<T> {

	public SeparaterChainingHashTable(){
		
	}
	
public SeparaterChainingHashTable(int size){
		theLists=new LinkedList[nextPrime(size)];
		for(int i=0;i<theLists.length;i++){
			theLists[i]=new LinkedList<T>();
		}
	}

	private List<T> [] theLists;
	private int currentSize;
	
	
	private static int nextPrime(int n){
		return 0;
	}
	
	public void makeEmpty(){
		for(int i=0;i<theLists.length;i++){
			theLists[i].clear();
		}
		
	}
}
