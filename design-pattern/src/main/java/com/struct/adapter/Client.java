/**
 * 
 */
package com.struct.adapter;

/**ÊÊÅäÆ÷Ä£Ê½
 * @author snoopy
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Target target=new Adapter(new Adaptee());
		
		target.sampleOperation1();
		target.sampleOperation2();
		

	}

}
