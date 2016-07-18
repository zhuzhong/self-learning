/**
 * 
 */
package com.zz.learning.zhujie.demo8;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class RedisServiceCloseAspect {

	@After("((execution(* com.zz.learning.zhujie.demo8.RedisService.*(..))) and (!execution(* com.zz.learning.zhujie.demo8.RedisService.close(..))))")
	public void close(JoinPoint jp){
		System.out.println("" + jp.getSignature().getName() + "()方法-结束！");
		//System.out.println(jp.getClass().getName()); //org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
		//System.out.println(jp.getTarget().getClass().getName());//com.zz.learning.zhujie.demo8.RedisServiceImpl
		//System.out.println(jp.getThis().getClass().getName());//com.zz.learning.zhujie.demo8.RedisServiceImpl$$EnhancerBySpringCGLIB$$fef993f1
		//Class proxyClazz=jp.getThis().getClass();
		Class proxyClazz=jp.getTarget().getClass();
		try {
			Method closeMethod=proxyClazz.getMethod("close");
			closeMethod.invoke(jp.getThis());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================");
	}
}
