/**
 * 
 */
package com.zz.learning.zhujie.demo3;

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
				"com/zz/learning/zhujie/demo3/spring.xml");
		UserService proxy = c.getBean("userServiceImpl", UserService.class);
		proxy.queryUser(1000L);
		proxy.insertUser(10000000L);
	}

}
