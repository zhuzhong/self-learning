/**
 * 
 */
package chapter10.p144;

/**
 * @author snoopy
 *
 */
public class StackImplTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StackImpl stack = new StackImpl(300);
		for (int i = 0; i < 120; i++) {
			if (!stack.push("zhuzhong" + i)) {
				break;
			}
		}

		System.out.println("ÊäÈëÍê³É...");
		System.out.println(stack.size());

		String value;
		while ((value = stack.pop()) != "underflow") {
			System.out.println(value);
		}

	}

}
