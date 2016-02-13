package com.test;

public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Thread t=new Thread(new RunnableImpl());
     
     t.start();
     t.interrupt();
     System.out.println("thread t name is "+t.getName());
     System.out.println("from main ThreadTest get thread name is "+Thread.currentThread().getName());
	}

	
	
	private static class RunnableImpl implements Runnable{

		public RunnableImpl(){
			System.out.println("from RunnableImpl coustructor thread name is "+Thread.currentThread().getName());
		}
		@Override
		public void run() {
			while(Thread.currentThread().isInterrupted()){
				
				try{
					System.out.println("from RunnableImpl get thread name is "+Thread.currentThread().getName());
					Thread.currentThread().sleep(100);
				}catch(InterruptedException e){
					System.out.println("interrupt error");
					return;
				}
			}
			
		}
		
	}
}
