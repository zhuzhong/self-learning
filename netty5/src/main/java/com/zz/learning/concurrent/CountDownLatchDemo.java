/**
 * 
 */
package com.zz.learning.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import akka.routing.GetRoutees;

/**
 * 模拟8 线程并发运行及线程等待
 * 
 * @author sunff
 *
 */
public class CountDownLatchDemo {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		CountDownLatchDemo d = new CountDownLatchDemo();
		d.getResult();
	}

	public void getResult() throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(8);
		CountDownLatch latch = new CountDownLatch(8);
		List<Future<List<String>>> result = new ArrayList<Future<List<String>>>();
		for (int i = 0; i < 8; i++) {

			Future<List<String>> r = es.submit(new Worker(latch, 100));
			result.add(r);
		}
		System.out.println(Thread.currentThread().getName() + " waiting...");
		latch.await();
		System.out.println("do next thing...");
		es.shutdown();
		System.out.println("print result");
		int i = 0;
		for (Future<List<String>> fls : result) {
			for (String s : fls.get()) {
				System.out.println((i++) + "--" + s);
			}
		}
	}

	public List<String> genArrayString(int no) {
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < no; i++) {
			r.add("string" + Math.random());
		}

		return r;
	}

	private class Worker implements Callable<List<String>> {

		private final CountDownLatch latch;
		private final int no;

		public Worker(CountDownLatch latch, int no) {
			this.latch = latch;
			this.no = no;
		}

		@Override
		public List<String> call() throws Exception {
			try {
				System.out.println(Thread.currentThread().getName()
						+ " running...");
				TimeUnit.SECONDS.sleep(2);
				return genArrayString(no);

			} finally {
				latch.countDown();
			}

		}
	}

}
