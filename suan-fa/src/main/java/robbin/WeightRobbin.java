/**
 * 
 */
package robbin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 按权重负载均衡算法
 * 
 * @author zhuzhong
 *
 */
public class WeightRobbin {

	private static List<VirtualMachine> vms = new ArrayList<VirtualMachine>();

	private static Integer totalWeight = 0; // 除以最大公约数之后的总权重

	public void init(List<MachineConfig> machines) {

		Integer[] weightArray = new Integer[machines.size()];
		for (int i = 0; i < machines.size(); i++) {
			weightArray[i] = machines.get(i).getWeight();
		}

		Integer gwd = gwd(weightArray);

		for (MachineConfig m : machines) {
			vms.add(new VirtualMachine(m.getHost(), m.getWeight(), gwd));
		}

		for (VirtualMachine v : vms) {
			totalWeight += v.getQuotient();
		}

	}

	private static AtomicInteger al = new AtomicInteger();

	public VirtualMachine getVm() {
		Integer requestTimes = al.incrementAndGet();
		requestTimes = requestTimes % totalWeight;

		for (int i = 0; i < vms.size(); i++) {
			VirtualMachine v = vms.get(i);
			requestTimes -= v.getQuotient();
			if (requestTimes < 0) {
				return v;
			}
		}

		return null;

	}

	/**
	 * 求两个数的最大公约数的欧几里得算法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private Integer euclid(Integer a, Integer b) {
		if (a < b) {
			Integer temp;
			temp = a;
			a = b;
			b = temp; // 换位置
		}
		if (0 == b) {
			return a;
		}
		return euclid(b, a % b);
	}

	/**
	 * 这个数组的最大公约数
	 * 
	 * @param weightArray
	 * @return
	 */
	private Integer gwd(Integer[] weightArray) {

		Integer g = weightArray[0];
		for (Integer num : weightArray) {
			g = euclid(g, num);
		}
		return g;
	}




}
