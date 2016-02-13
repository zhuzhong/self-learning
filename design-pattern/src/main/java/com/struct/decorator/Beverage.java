/**
 * 
 */
package com.struct.decorator;

/**
 * @author snoopy
 *
 */
public abstract class Beverage {

	
	String description="unknow beverage";
	
	//ÃèÊö
	public String getDescription(){
		return this.description;
	}
	
	//³É±¾
	public abstract double cost();
	
}
