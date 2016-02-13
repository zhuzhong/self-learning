package javathreads.examples.ch06.concu.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	// 只能5个线程同时访问
	Semaphore semp;

	public static void main(String[] args) {
		SemaphoreTest t =new SemaphoreTest();
		t.test();
	}

	private void test() {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		semp = new Semaphore(5);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			exec.execute(new RunnableImpl(index));
		}
		// 退出线程池
		exec.shutdown();
	}

	private class RunnableImpl implements Runnable {

		private int n;
		public RunnableImpl(int n){
			this.n=n;
		}
		@Override
		public void run() {
			try {
				// 获取许可
				semp.acquire();
				System.out.println("Accessing: " + n);
				Thread.sleep((long) (Math.random() * 10000));
				// 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
				semp.release();
			} catch (InterruptedException e) {
			}
		}

	}
}