package com.pc.tet;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Producer2 {

	
	private BlockingQueue<Integer> bq;
	private ExecutorService service;
	public Producer2(BlockingQueue<Integer> bq){
		this.bq=bq;
		this.service=Executors.newCachedThreadPool();
	}
	public void start(){
		for(int i=0;i<5;i++){
			service.execute(new InnerProduct());
		}
	}
	
	
	private class InnerProduct implements Runnable{
		private Random r;
		public InnerProduct(){
			r=new Random();
		}
		@Override
		public void run() {
			
			while(true){
				try {
					System.out.println(Thread.currentThread().getName());
					bq.put(r.nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
}
