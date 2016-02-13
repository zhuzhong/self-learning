/**
 * 
 */
package com.single;

/**
 * @author snoopy
 *
 */
public class SingleTest {

	private SingleTest(){}
	
	public SingleTest getInstance(){
		return SingleHolder.instance;
	}
	
	private static  class SingleHolder{
		public static final SingleTest instance=new SingleTest();
	}
}
