package javathreads.examples.ch06.concu.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest { // ...
	public static void main(String args[]) throws InterruptedException {
		CountDownLatchTest t = new CountDownLatchTest();
		t.test();
	}

	private void test() throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(10);

		for (int i = 0; i < 10; ++i)
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		doSomethingElse(); // don't let run yet
		startSignal.countDown(); // let all threads proceed
		doSomethingElse();
		doneSignal.await(); // wait for all to finish
	}

	private void doSomethingElse() {
		System.out.println("doSomethingElse....");
	}

	private static class Worker implements Runnable {
		private final CountDownLatch startSignal;
		private final CountDownLatch doneSignal;

		Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
			this.startSignal = startSignal;
			this.doneSignal = doneSignal;
		}

		public void run() {
			try {
				startSignal.await();
				doWork();
				doneSignal.countDown();
			} catch (InterruptedException ex) {
			} // return;
		}

		private void doWork() {
			System.out.println(Thread.currentThread().getName() + " test...");
		}
	}
}
