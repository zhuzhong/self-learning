/**
 * 
 */
package com.zz.learning.zhujie.demo5;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class AspectDemo5 {

	@Before("@args(com.zz.learning.zhujie.demo5.Monitorable)")
	public void value() {
		System.out.println("i am aspectdemo5");
	}
}
