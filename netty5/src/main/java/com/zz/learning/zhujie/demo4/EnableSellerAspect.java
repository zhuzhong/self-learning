/**
 * 
 */
package com.zz.learning.zhujie.demo4;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class EnableSellerAspect {

	@DeclareParents(value = "com.zz.learning.zhujie.demo4.NaiveWaiter4", defaultImpl = SmartSeller.class)
	public Seller seller;
}
