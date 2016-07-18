/**
 * 
 */
package com.zz.learning.zhujie.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class PreGreetingAspect {

	@Before("execution(* greetTo(..))")
	public void beforeGreeting() {
		System.out.println("how are you");
	}

	public Object aroundGreeting(ProceedingJoinPoint point) {
		
		return point;

	}

}
