/**
 * 
 */
package com.struct.decorator;

/**
 * @author snoopy
 *
 */
public  class CondimentDecorator extends Beverage {

	
	protected Beverage beverage;
	protected CondimentDecorator(Beverage beverage){
		this.beverage=beverage;
	}
	@Override
	public double cost() {
		return beverage.cost();
	}
	
	public String getDescription(){
		return beverage.getDescription();
	}

}
