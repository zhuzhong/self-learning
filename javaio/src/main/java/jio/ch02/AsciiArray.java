package jio.ch02;

import java.io.IOException;

public class AsciiArray {

	public static void main(String[] args) {
  long now=System.currentTimeMillis();
		byte[] b = new byte[(127 - 31) * 2];
		int index = 0;
		for (int i = 32; i < 127; i++) {
			b[index++] = (byte) i;
			// Break line after every eight characters.
			if (i % 8 == 7)
				b[index++] = (byte) '\n'; //回车换行
			else
				b[index++] = (byte) '\t';//缩进
		}
		b[index++] = (byte) '\n';
		try {
			System.out.write(b);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		now=System.currentTimeMillis()-now;
		System.out.println("total time is "+now);
	}
}
