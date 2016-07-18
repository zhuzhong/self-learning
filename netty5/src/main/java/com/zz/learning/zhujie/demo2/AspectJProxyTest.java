/**
 * 
 */
package com.zz.learning.zhujie.demo2;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class AspectJProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AspectJProxyTest t = new AspectJProxyTest();
		t.test2();
	}

	public void test2() {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(
				"com/zz/learning/zhujie/demo2/spring.xml");
		Waiter proxy = c.getBean("naiveWaiter", Waiter.class);
		proxy.greetTo("john");
		proxy.serverTo("john");
	}

	public void test1() {
		Waiter target = new NaiveWaiter();
		AspectJProxyFactory factory = new AspectJProxyFactory();
		factory.setTarget(target);
		factory.addAspect(PreGreetingAspect.class);
		Waiter proxy = factory.getProxy();
		proxy.greetTo("john");
		proxy.serverTo("john");

	}
}
