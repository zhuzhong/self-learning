package com.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest2 {

	public static void main(String[] args) {

		ThreadTest2 t = new ThreadTest2();
		t.testRunnableImpl3();

	}

	
	
	private  void testRunnableImpl3(){
		RunnableImpl3 r=new RunnableImpl3();
		Thread t=new Thread(r);
		t.start();
		ReadThread readT=new ReadThread(r);
		readT.start();
		System.out.println("read i value from main thread r="+r.getI());
	}
	
	public class ReadThread extends Thread{
		private RunnableImpl3 r;
		public ReadThread(RunnableImpl3 r){
			this.r=r;
		}
		@Override
		public void run() {
			//while(true)
			System.out.println("read i value r="+r.getI());
		}
	}
	
	
	public class RunnableImpl3 implements Runnable{

		
		private int i=0;
		@Override
		public void run() {
			System.out.println("set i value");
			setI(100);
			
		}
		public synchronized int getI() {
			return i;
		}
		public synchronized void setI(int i) {
			this.i = i;
		}
		
		
		
	}
	private void testCallableImpl() {
		CallableImpl c = new CallableImpl();
		FutureTask<Integer> f = new FutureTask<Integer>(c);
		Thread t = new Thread(f);
		t.start();

		try {
			System.out.println(f.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(c.getI());

	}

	public class CallableImpl implements Callable<Integer> {

		private int i = 0;

		@Override
		public Integer call() throws Exception {
			i++;
			return i;
		}

		public Integer getI() {

			return i;
		}
	}

	private void testRunnable() {
		RunnableImpl r = new RunnableImpl();
		Thread t = new Thread(r);
		t.start();
		System.out.println(r.getI());
	}

	private void testSubThread() {
		AtomicInteger i = new AtomicInteger(0);
		SubThread t = new SubThread(i);
		t.start();
		System.out.println(t.getI());
		System.out.println(i);
	}

	public class SubThread extends Thread {
		private AtomicInteger i;

		public SubThread(AtomicInteger i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("i value in before run is " + i);
			i.getAndIncrement();
			System.out.println("i value in after run is " + i);
		}

		public AtomicInteger getI() {

			return i;
		}
	}

	public class RunnableImpl implements Runnable {

		private int i = 0;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// for(int j=0;j<10;j++){
			// i++;
			// }
			System.out.println("i value in before run is " + i);
			i++;
			System.out.println("i value in after run is " + i);
		}

		public int getI() {

			return i;
		}

	}
}
