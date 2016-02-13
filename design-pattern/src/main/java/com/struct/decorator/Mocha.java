/**
 * 
 */
package com.struct.decorator;

/**
 * @author snoopy
 *
 */
public class Mocha extends CondimentDecorator {

	
	
	
	protected Mocha(Beverage beverage) {
		super(beverage);
		
	}

	@Override
	public String getDescription() {
		
		return super.getDescription()+" "+"add Mocha";
	}

	
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return super.cost()+0.20;
	}

}
