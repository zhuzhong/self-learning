/**
 * 
 */
package c.z.nio.buffers;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author sunff
 *
 */

public class ByteBufferTest {

	static ByteBuffer b = null;

	@BeforeClass
	public static void init() {
		b = ByteBuffer.allocate(10);
	}

	@Test
	public void put() {
		b.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l')
				.put((byte) 'w');
		System.out.println(b.toString());
		b.flip();
		System.out.println(b.toString());
		System.out.println((char)b.get());
		System.out.println(b.toString());

	}

	@Test
	public void test2() {
		String str = "helloWorld";
		ByteBuffer buff = ByteBuffer.wrap(str.getBytes());
		System.out.println("position:" + buff.position() + "\t limit:"
				+ buff.limit());
		// 读取两个字节
		buff.get();
		buff.get();
		System.out.println("position:" + buff.position()
				+ "\t limit:" + buff.limit());
		buff.mark();
		System.out.println("position:" + buff.position() + "\t limit:"
				+ buff.limit());
		buff.flip();
		System.out.println("position:" + buff.position() + "\t limit:"
				+ buff.limit());
	}
	
	

}
