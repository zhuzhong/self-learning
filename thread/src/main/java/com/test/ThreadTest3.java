package com.test;

public class ThreadTest3 {

	public static void main(String[] args) {
		ThreadTest3 t = new ThreadTest3();
		t.testRunnableImpl();
	}

	private void testRunnableImpl() {
		RunnableImpl r = new RunnableImpl();
		Thread runThread = new Thread(r);
		SubThread stopThread=new SubThread(r);
		stopThread.start();
		runThread.start();
	}

	public class SubThread extends Thread {
		private RunnableImpl r;

		public SubThread(RunnableImpl r) {
			this.r = r;
		}

		@Override
		public void run() {
			System.out.println("I am stopping another thread...");
			r.stopThread();
		}
	}

	public class RunnableImpl implements Runnable {

		private volatile boolean done;

		@Override
		public void run() {
			while (!done) {
				System.out.println("I am running...");
			}
			System.out.println("I am stopping...");
		}

		public void stopThread() {
			this.done = true;
		}
	}

}
