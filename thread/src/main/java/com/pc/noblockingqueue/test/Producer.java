package com.pc.noblockingqueue.test;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Producer extends Thread {

	private ConcurrentLinkedQueue<Integer> bq;
	private Random r;

	public Producer() {
		this.bq = new ConcurrentLinkedQueue<Integer>();
		r = new Random();
	}

	
	public Producer(ConcurrentLinkedQueue<Integer> bq) {
		this.bq = bq;
		r = new Random();
	}
	@Override
	public void run() {

		while (true ) {
			
			
			
			
			Integer i=r.nextInt(100);
			bq.offer(i);
			System.out.println(Thread.currentThread().getName()
					+ " put i value ="+i);

		}
	}

	public ConcurrentLinkedQueue<Integer> getBq() {
		return bq;
	}




}
