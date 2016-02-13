/**
 * 
 */
package com.pc.disruptor.ex2;

/**
 * @author sunff
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListConsumer c=new ListConsumer();
		c.consume();
		ListProductor p=new ListProductor(c.getRingBuffer());
		p.product();
		

	}

}
