/**
 * 
 */
package com.zz.learning.zhujie.demo4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t = new Test();
		t.test2();
	}

	public void test2() {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(
				"com/zz/learning/zhujie/demo4/spring.xml");
		Waiter4 proxy = c.getBean("naiveWaiter4", Waiter4.class);
		proxy.greetTo("zhuzhong");

		Seller s = (Seller) proxy;
		s.sell(1000L);
	}

}
