/**
 * 
 */
package test;

import java.nio.CharBuffer;

/**
 * @author snoopy
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.print((int) (char) (byte) -1);
//		CharBuffer a=CharBuffer.allocate(20);
//		a.put("com3dy");
//		a.flip();
//		CharBuffer b=CharBuffer.allocate(20);
//		b.put("com3dy");
//		b.flip();
//		b.position(2);
//		System.out.println(a.compareTo(b));
		
		char[] t={'a','b','c'};
		CharBuffer c=CharBuffer.wrap(t, 1, 2);
		System.out.println(c.arrayOffset());
		
		CharBuffer c2=CharBuffer.wrap(t);
		System.out.println(c2.arrayOffset());
		
		CharSequence cs="hello world";
	
	}

	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {

			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
}
