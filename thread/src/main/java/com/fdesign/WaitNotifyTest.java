/**
 * 
 */
package com.fdesign;

/**这个是简单的利用wait notify机制，将 RunnableImpl中执行的结果，传递给主线程中，
 * 这里需要利用线程的等待－通知机制，并且需要锁
 * @author snoopy
 *
 */
public class WaitNotifyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WaitNotifyTest t=new WaitNotifyTest();
		t.test();
	}

	
	private void test(){
		
		RunnableImpl r = new RunnableImpl();
		new Thread(r).start();
		synchronized (r) {
			while(true){
				try {
					r.wait();
					System.out.println(Thread.currentThread().getName()+"  new value is "+r.getNewValue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		}
		
	
		
	}
	public static class RunnableImpl implements Runnable {

		private int newValue;

		@Override
		public void run() {
			synchronized (this) {
				while(true){
					try {
						wait(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					newValue++;
					System.out.println(Thread.currentThread().getName()+" new value is "+newValue);
					this.notifyAll();
				}
				
			}

		}

		public int getNewValue() {
			return newValue;
		}

	}
}
