/**
 * 
 */
package test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author snoopy
 *
 */
public class DelayQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	private void test(){
		DelayQueue<Delayed> dq=new DelayQueue<Delayed>();
	}
	
	
	
	private class SubDelayed implements Delayed{

		@Override
		public int compareTo(Delayed arg0) {

			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return 0;
		}
		
	}
}
