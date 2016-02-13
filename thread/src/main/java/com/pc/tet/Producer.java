package com.pc.tet;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer extends Thread {

	private BlockingQueue<Integer> bq;
	private Random r;

	public Producer() {
		this.bq = new LinkedBlockingQueue<Integer>(100);
		r = new Random();
	}

	
	public Producer(BlockingQueue<Integer> bq) {
		this.bq = bq;
		r = new Random();
	}
	@Override
	public void run() {

		while (true ) {
			try {
				System.out.println(Thread.currentThread().getName()
						+ " put i value");
				bq.put(r.nextInt(100));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public BlockingQueue<Integer> getBq() {
		return bq;
	}




}
