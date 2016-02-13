package com.pc.tet;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer2 {
	private BlockingQueue<Integer> bq;

	private ExecutorService service;
	public Consumer2(BlockingQueue<Integer> bq) {
		this.bq = bq;
		this.service=Executors.newCachedThreadPool();
	}

	
	public void start(){
		for(int i=0;i<3;i++){
			service.execute(new InnerConsumer());
		}
	}
	
	private class InnerConsumer implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					System.out.println(Thread.currentThread().getName()+"-"+bq.take());
					bq.take();
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
