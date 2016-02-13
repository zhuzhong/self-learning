/**
 * 
 */
package com.struct.decorator;

/**
 * @author snoopy
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Beverage b=new Mocha(new Espresso());
		System.out.println( b.getDescription());
		
		System.out.println( b.cost());
	}

}
