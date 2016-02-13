/**
 * 
 */
package com.test;

/**
 * @author Administrator
 *
 */
public class BruteForceCoding {

	private static byte byteval = 101;
	private static short shortval = 1001;
	private static int intval = 100000001;
	private static long longval = 100000000001L;

	private final static int bsize = Byte.SIZE;
	private final static int ssize = Short.SIZE;
	private final static int isize = Integer.SIZE;
	private final static int lsize = Long.SIZE;

	//作用防止在字节数组转换成int类型时，发生符号扩展，即转换成无符号整数
	private final static int bytemask = 0xFF;

	private static String byteArrayToDecimalString(byte[] barray) {
		StringBuilder sb=new StringBuilder();
		for(byte b:barray){
			sb.append(b&bytemask).append(" ");
		}
		return sb.toString();
	}
	

	private static int encodeIntBigEndian(byte[] dst,long val,int offset,int size){
		return 0;
	}
	
	
}
