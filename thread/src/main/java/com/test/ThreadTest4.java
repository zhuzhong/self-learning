package com.test;

public class ThreadTest4 {

	public static void main(String[] args) {
		SubThread s=new SubThread();
		s.run();
        System.out.println("In ThreadTest4,Thread name is "+Thread.currentThread().getName());
	}
	public static class SubThread extends Thread{
		@Override
		public void run(){
			System.out.println("In SubThread,Thread name is "+Thread.currentThread().getName());
		}
	}
}
