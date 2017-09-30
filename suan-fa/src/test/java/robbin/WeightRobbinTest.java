package robbin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author zhuzhong
 *
 */
public class WeightRobbinTest {

	@Test
	public void getVm() {
		System.out.println("########################################################");
		List<MachineConfig> machines = new ArrayList<MachineConfig>();
		for (int i = 0; i < 10; i++) {
			int j = 2 * i + 4;
			MachineConfig m = new MachineConfig("192.168.10." + j, j);
			System.out.println(m);
			machines.add(m);
		}

		System.out.println("---------------------------------------------------------");

		WeightRobbin r = new WeightRobbin();
		r.init(machines);
		for (int i = 0; i < 70; i++) {
			VirtualMachine v = r.getVm();
			System.out.println(v.toString());
		}
	}
}
