package com.pc.noblockingqueue.test;

import java.util.concurrent.ConcurrentLinkedQueue;

public  class Consumer extends Thread {

	private  ConcurrentLinkedQueue<Integer> bq;

	public Consumer(ConcurrentLinkedQueue<Integer> bq) {
		this.bq = bq;
	}

	public void run() {
		while (true) {
			Integer i=bq.poll();
			if(i!=null){
				System.out.println(Thread.currentThread().getName()
						+ " get i value is  " + i);
			}
			
		}

	}
}