/**
 * 
 */
package com.zz.learning.zhujie.demo6;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 命名切点
 * 
 * @author Administrator
 *
 */
public class TestNamePointcut {

	@Pointcut("within(com.test.*)")
	private void inPackage() {
	}

	@Pointcut("execution(* greetTo(..))")
	protected void greetTo() {
	}

	@Pointcut("inPackage() and greetTo()")
	public void inPkgGreetTo() {
	}
}
