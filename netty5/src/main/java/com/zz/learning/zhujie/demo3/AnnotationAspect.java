/**
 * 
 */
package com.zz.learning.zhujie.demo3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class AnnotationAspect {

	@Before(value = "@annotation(com.zz.learning.zhujie.demo3.UserAnnotation)")
	public void beforeQuery() {
		System.out.println("before query...");
	}

}
