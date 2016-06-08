/**
 * 
 */
package com.zz.learning.zhujie.demo5;

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
				"com/zz/learning/zhujie/demo5/spring.xml");
		Tservice proxy = c.getBean("tservice", Tservice.class);

		//proxy.hello(new T1());
		//proxy.hello(new T1());
		
		
	//	proxy.hello(new T2());
		
		
		proxy.hello(new T3());
	}

} 
