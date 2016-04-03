/**
 * 
 */
package ch15;

import java.util.concurrent.TimeUnit;

/**
 * @author sunff
 *
 */
public class GSessionId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			initializeNextSession(1L);
			try {
				TimeUnit.MICROSECONDS.sleep(1L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void initializeNextSession(long machinedId) {
		long nextid = 0;
		nextid = (System.currentTimeMillis() << 24) >>> 8;
		nextid = nextid | (machinedId << 56);
		// return nextid;
		System.out.println(nextid);
	}
}
