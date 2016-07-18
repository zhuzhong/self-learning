/**
 * 
 */
package com.zz.learning.zhujie.demo8;

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
				"com/zz/learning/zhujie/demo8/spring.xml");
		TestClass proxy = c.getBean("testClass", TestClass.class);
		proxy.t();
	}

}
