/**
 * 
 */
package com.zz.learning.sl;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sunff
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext c=
				new ClassPathXmlApplicationContext("com/zz/sl/spring.xml");
		UserService us=c.getBean("userServiceImpl", UserService.class);
		User u=new User();
		u.setPass("test");
		u.setUserName("zzz");
		Long userId=us.register(u);
		System.out.println("userId="+userId);

		try {
			TimeUnit.SECONDS.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
