/**
 * 
 */
package com.pc.tet;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



/**
 * @author sunff
 *
 */
public class MainTest {

	public static void main(String args[]) {
		BlockingQueue<Integer> bq=new LinkedBlockingQueue<Integer>(1000);
		Producer2 p = new Producer2(bq);
		p.start();



		new Consumer2(bq).start();
		//new Consumer(bq).start();
		//new Consumer(bq).start();

	}

}
