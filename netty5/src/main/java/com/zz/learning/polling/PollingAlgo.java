/**
 * 
 */
package com.zz.learning.polling;

/**
 * 普通轮询算法
 * 
 * @author Administrator
 *
 */
public class PollingAlgo {

	private int currentIndex;

	private int[] hosts;
	int hostNo = 0;

	public void init() {
		hostNo = 10;
		currentIndex = hostNo - 1;
		hosts = new int[hostNo];
	}

	public int getNext() {
		int j = currentIndex;
		do {
			j = (currentIndex + 1) % hostNo;
			currentIndex = j;
			return hosts[currentIndex];
		} while (j != currentIndex);
		// return hostNo - 1;
	}
}
