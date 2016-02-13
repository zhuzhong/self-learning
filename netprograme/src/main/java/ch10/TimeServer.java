package ch10;

import java.net.*;
import java.io.*;
import java.util.Date;

public class TimeServer {

	public final static int DEFAULT_PORT = 37;

	public static void main(String[] args) {

		// System.out.println("init.....");
		// sendTime3(args);
		// System.out.println(getUnSignedLong(3623102490L));
		// /sendTime4(args);
//		test1();
//		test12();
//		test2();
//		longToByte(3628459596L);
//		long2Bytes(3628459596L);
		sendTime(args);
	} // end main

	private static void test2() {
		//int n;
		//Scanner s = new Scanner(System.in);
		
		System.out.println("please input a number:");
		//n = s.nextInt();
        long n=3628459596L;
		for (int i = 31; i >= 0; i--) {
			if ((n & (1 << i)) != 0) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
			if ((32 - i) % 8 == 0) {
				System.out.print(" ");
			}
		}

	}

	private static void test12() {

		long differenceBetweenEpochs = 2208988800L;
		Date now = new Date();
		long msSince1970 = now.getTime();
		long secondsSince1970 = msSince1970 / 1000;
		long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
		System.out.println("secondsSince1900 value is " + secondsSince1900);
		/**
		 * 说明，因为需要发送出去的是4字节big-endian无符号整数，
		 * 那么需要作的
		 * １.先将整数转化为无符号整数，这个通过移位来实现
		 */
		byte[] time = new byte[4];
		time[0] = (byte) ((secondsSince1900 >> 24) &0xFF);//将数据右移24位，取剩下的数据最低8位
		time[1] = (byte) ((secondsSince1900 >> 16)& 0xFF );//将数据右移16位，取剩下的数据最低8位
		time[2] = (byte) ((secondsSince1900 >>8)& 0xFF);//将数据右移8位，再取剩下数据的最低8位
		time[3] = (byte) (secondsSince1900 & 0xFF); //取数据secondsSince1900的最低8位
		
//		   bos.write((int) ((resultSecs >> 24) & 0xFF));
//		   bos.write((int) ((resultSecs >> 16) & 0xFF));
//		   bos.write((int) ((resultSecs >> 8) & 0xFF));
//		   bos.write((int) (resultSecs & 0xFF));
		   
		for(byte b:time){
			System.out.println(b);
		}

	}
	private static void test1() {

		long differenceBetweenEpochs = 2208988800L;
		Date now = new Date();
		long msSince1970 = now.getTime();
		long secondsSince1970 = msSince1970 / 1000;
		long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
		System.out.println("secondsSince1900 value is " + secondsSince1900);
		byte[] time = new byte[4];
		time[0] = (byte) ((secondsSince1900 & 0x00000000FF000000L) >> 24);
		time[1] = (byte) ((secondsSince1900 & 0x0000000000FF0000L) >> 16);
		time[2] = (byte) ((secondsSince1900 & 0x000000000000FF00L) >> 8);
		//取得secondsSince1900的最低8位
		time[3] = (byte) (secondsSince1900 & 0x00000000000000FFL);
		for(byte b:time){
			System.out.println(b);
		}

	}

	private static void sendTime4(String[] args) {

		int port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
				if (port < 0 || port >= 65536) {
					System.out.println("Port must between 0 and 65535");
					return;
				}
			} catch (NumberFormatException ex) {
			}
		}

		// The time protocol sets the epoch at 1900,
		// the java Date class at 1970. This number
		// converts between them.

		long differenceBetweenEpochs = 2208988800L;

		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket connection = null;
				try {
					connection = server.accept();
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					long secondsSince1970 = msSince1970 / 1000;
					long secondsSince1900 = secondsSince1970
							+ differenceBetweenEpochs;
					System.out.println("secondsSince1900 value is "
							+ secondsSince1900);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					DataOutputStream dos = new DataOutputStream(baos);
					dos.writeLong(secondsSince1900);
					dos.flush();

					System.out.println(baos.toString("UTF-8"));
					out.write(baos.toByteArray());

					out.flush();

				} // end try
				catch (IOException ex) {
				} // end catch
				finally {
					if (connection != null)
						connection.close();
				}
			} // end while
		} // end try
		catch (IOException ex) {
			System.err.println(ex);
		} // end catch
	}

	public static long getUnSignedLong(long l) {
		return getLong(longToDword(l), 0);
	}

	// 将long型数据转换为Dword的字节数组（C/C++的无符号整数）
	private static byte[] longToDword(long value) { // 3623102490

		byte[] data = new byte[4];

		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (value >> (8 * i));
		}

		return data;
	}
	
	
	public static byte[] long2Bytes(long num) {  
	    byte[] byteNum = new byte[8];  
	    for (int ix = 0; ix < 8; ++ix) {  
	        int offset = 64 - (ix + 1) * 8;  
	        byteNum[ix] = (byte) ((num >> offset) & 0xff);  
	    }  
	    return byteNum;  
	}  
	
	private static byte[] longToByte(long value){
		byte[] b = new byte[4];
		try {
			b = java.lang.Long.toString(value).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	// 将C/C++的无符号 DWORD类型转换为java的long型
	private static long getLong(byte buf[], int index) {

		int firstByte = (0x000000FF & ((int) buf[index]));
		int secondByte = (0x000000FF & ((int) buf[index + 1]));
		int thirdByte = (0x000000FF & ((int) buf[index + 2]));
		int fourthByte = (0x000000FF & ((int) buf[index + 3]));

		long unsignedLong = ((long) (firstByte | secondByte << 8
				| thirdByte << 16 | fourthByte << 24)) & 0xFFFFFFFFL;

		return unsignedLong;
	}

	private static void sendTime3(String[] args) {

		int port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
				if (port < 0 || port >= 65536) {
					System.out.println("Port must between 0 and 65535");
					return;
				}
			} catch (NumberFormatException ex) {
			}
		}

		// The time protocol sets the epoch at 1900,
		// the java Date class at 1970. This number
		// converts between them.

		long differenceBetweenEpochs = 2208988800L;

		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket connection = null;
				try {
					connection = server.accept();
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					long secondsSince1970 = msSince1970 / 1000;
					long secondsSince1900 = secondsSince1970
							+ differenceBetweenEpochs;

					OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
					// w.write(secondsSince1900);
					for (int i = 0; i < 10; i++) {
						w.write("我是朱忠" + i + "\n");
					}

					w.flush();
				} // end try
				catch (IOException ex) {
				} // end catch
				finally {
					if (connection != null)
						connection.close();
				}
			} // end while
		} // end try
		catch (IOException ex) {
			System.err.println(ex);
		} // end catch
	}

	private static void sendTime2(String[] args) {

		int port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
				if (port < 0 || port >= 65536) {
					System.out.println("Port must between 0 and 65535");
					return;
				}
			} catch (NumberFormatException ex) {
			}
		}

		// The time protocol sets the epoch at 1900,
		// the java Date class at 1970. This number
		// converts between them.

		long differenceBetweenEpochs = 2208988800L;

		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket connection = null;
				try {
					connection = server.accept();
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					long secondsSince1970 = msSince1970 / 1000;
					long secondsSince1900 = secondsSince1970
							+ differenceBetweenEpochs;
					// byte[] time = new byte[1];
					// time[0] = (byte) ((secondsSince1900 &
					// 0x00000000FF000000L) >> 24);
					// time[1] = (byte) ((secondsSince1900 &
					// 0x0000000000FF0000L) >> 16);
					// time[2] = (byte) ((secondsSince1900 &
					// 0x000000000000FF00L) >> 8);
					// time[3] = (byte) (secondsSince1900 &
					// 0x00000000000000FFL);
					// time[0]=Long.valueOf(secondsSince1970).byteValue();
					// out.write(time);
					DataOutputStream dout = new DataOutputStream(out);
					System.out.println("secondsSince1900 value is "
							+ secondsSince1900);// 3623102490
					dout.writeLong(secondsSince1900);// 1414113446
					// dout.writeUTF("我是朱忠");
					dout.flush();
					out.flush();

				} // end try
				catch (IOException ex) {
				} // end catch
				finally {
					if (connection != null)
						connection.close();
				}
			} // end while
		} // end try
		catch (IOException ex) {
			System.err.println(ex);
		} // end catch
	}

	private static void sendTime(String[] args) {

		int port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
				if (port < 0 || port >= 65536) {
					System.out.println("Port must between 0 and 65535");
					return;
				}
			} catch (NumberFormatException ex) {
			}
		}

		// The time protocol sets the epoch at 1900,
		// the java Date class at 1970. This number
		// converts between them.

		long differenceBetweenEpochs = 2208988800L;

		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket connection = null;
				try {
					connection = server.accept();
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					//3658963178
					 msSince1970 = 1449976733464L;
					long secondsSince1970 = msSince1970 / 1000;
					long secondsSince1900 = secondsSince1970
							+ differenceBetweenEpochs;
					System.out.println("secondsSince1900 value is "
							+ secondsSince1900);
					/*
					 * 数据传输是以二进制码的形式，所以只要保证二进制码不变，就可以保证值不会发生偏移
					 */
					byte[] time = new byte[4];
					time[0] = (byte) ((secondsSince1900 & 0x00000000FF000000L) >> 24);
					
					time[1] = (byte) ((secondsSince1900 & 0x0000000000FF0000L) >> 16);
					time[2] = (byte) ((secondsSince1900 & 0x000000000000FF00L) >> 8);
					time[3] = (byte) (secondsSince1900 & 0x00000000000000FFL);
					System.out.println(time[0]);
					System.out.println(time[1]);
					System.out.println(time[2]);
					System.out.println(time[3]);
					out.write(time);

					out.flush();
					
					

				} // end try
				catch (IOException ex) {
				} // end catch
				finally {
					if (connection != null)
						connection.close();
				}
			} // end while
		} // end try
		catch (IOException ex) {
			System.err.println(ex);
		} // end catch
	}
} // end TimeServer
