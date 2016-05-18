/**
 * 
 */
package com.zz.learning.polling;

import java.util.ArrayList;
import java.util.List;

/**
 * 有权重的轮询算法
 * 
 * @author Administrator
 */
public class WeightPollingAlgo {

	List<Machine> list = null;
	int gcd = 0;
	int maxWeight = 0;

	int n = 0;

	int i = -1;
	int cw = 0;

	public Machine getNext() {
		while (true) {
			i = (i + 1) % n;

			if (i == 0) {
				cw = cw - gcd;
				if (cw <= 0) {
					cw = maxWeight;
					if (cw == 0) {
						return null;
					}
				}
			}

			if (list.get(i).weight >= cw) {
				return list.get(i);
			}
		}
	}

	public void init() {
		list = new ArrayList<Machine>();
		gcd = calGcd(list);
		maxWeight = calMaxWeight(list);
		n = list.size();
	}

	private int calMaxWeight(List<Machine> list2) {
		return 0;
	}

	private int calGcd(List<Machine> list) {
		return 0;
	}

	private static class Machine {
		public int machineNo; // 编号
		public int weight; // 权重
	}
}
