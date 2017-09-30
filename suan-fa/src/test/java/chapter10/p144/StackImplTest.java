/**
 * 
 */
package chapter10.p144;

import org.junit.Test;

/**
 * @author snoopy
 *
 */
public class StackImplTest{

	@Test
	public void testPush(){
		StackImpl stack = new StackImpl(300);
		for (int i = 0; i < 120; i++) {
			if (!stack.push("zhuzhong" + i)) {
				break;
			}
		}

		//System.out.println("输入完成...");
		//System.out.println(stack.size());
	}
	
	
	@Test
	public void testPop(){
		StackImpl stack = new StackImpl(300);
		for (int i = 0; i < 120; i++) {
			if (!stack.push("zhuzhong" + i)) {
				break;
			}
		}

		//System.out.println("输入完成...");
		//System.out.println(stack.size());

		String value;
		while ((value = stack.pop()) != "underflow") {
			System.out.println(value);
		}

	}
	/**
	 * @param args
	 *//*
	public static void main(String[] args) {
		StackImpl stack = new StackImpl(300);
		for (int i = 0; i < 120; i++) {
			if (!stack.push("zhuzhong" + i)) {
				break;
			}
		}

		System.out.println("输入完成...");
		System.out.println(stack.size());

		String value;
		while ((value = stack.pop()) != "underflow") {
			System.out.println(value);
		}

	}*/

}
