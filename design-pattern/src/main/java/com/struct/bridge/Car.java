/**
 * 
 */
package com.struct.bridge;

/**
 * @author snoopy
 *
 */
public class Car extends Vehicle {

	private Road road;
	public Car(Road road){
		this.road=road;
	}
	@Override
	public void run() {
		road.run();
		System.out.println("  С�೵  ");

	}

}
