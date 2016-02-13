/**
 * 
 */
package com.test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author snoopy
 *
 */
public class AtomicTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AtomicReference<String> a=new AtomicReference<String>("test");
		
		String oldValue=a.getAndSet("oook");
		System.out.println(oldValue);
	}

}
