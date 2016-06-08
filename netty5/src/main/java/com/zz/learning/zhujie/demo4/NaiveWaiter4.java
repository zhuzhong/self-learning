/**
 * 
 */
package com.zz.learning.zhujie.demo4;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class NaiveWaiter4 implements Waiter4 {

	@Override
	public void greetTo(String name) {
		System.out.println("hello " + name);

	}

}
