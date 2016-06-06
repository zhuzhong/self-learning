/**
 * 
 */
package com.zz.learning.zhujie.demo2;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public class NaiveWaiter implements Waiter {

	@Override
	public void greetTo(String clientName) {
		System.out.println("naive wiater.greetTo");

	}

	@Override
	public void serverTo(String clientName) {
		// TODO Auto-generated method stub
		System.out.println("naive waiter.serverTo");
	}

}
