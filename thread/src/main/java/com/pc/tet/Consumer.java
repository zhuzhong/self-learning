package com.pc.tet;

import java.util.concurrent.BlockingQueue;

public  class Consumer extends Thread {

	private BlockingQueue<Integer> bq;

	public Consumer(BlockingQueue<Integer> bq) {
		this.bq = bq;
	}

	public void run() {
		while (true) {
			try {
				System.out.println(Thread.currentThread().getName()
						+ " i value is  " + bq.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}