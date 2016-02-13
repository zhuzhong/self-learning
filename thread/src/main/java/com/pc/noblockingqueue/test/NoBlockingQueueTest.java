package com.pc.noblockingqueue.test;

public class NoBlockingQueueTest {

	public static void main(String[] args) {
		Producer p=new Producer();
		p.start();
		
		for(int i=0;i<10;i++){
			Consumer c=new Consumer(p.getBq());
			c.start();
		}
	
	}

}
