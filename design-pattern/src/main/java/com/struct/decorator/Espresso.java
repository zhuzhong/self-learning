/**
 * 
 */
package com.struct.decorator;

/**
 * @author snoopy
 *
 */
public class Espresso extends Beverage {

	public Espresso(){
		this.description="espresso";
	}
	@Override
	public double cost() {
		
		return 1.99;
	}

}
