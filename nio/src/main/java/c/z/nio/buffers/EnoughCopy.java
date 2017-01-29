/**
 * 
 */
package c.z.nio.buffers;

import java.nio.CharBuffer;

/**
 * @author sunff
 *
 */
public class EncoughCopy {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String s = "abcdefghi";
        CharBuffer cb = CharBuffer.allocate(6);
        for (int i = 0; i < 6; i++) {
            cb.put(s.charAt(i));
        }
        cb.flip();
        System.out.println("first="+cb);
       // System.out.println(cb.get());

        char[] cs = new char[3];
        cb.get(cs); //读入

        System.out.println("second="+cb.toString());
        System.out.println(cs);

    }

}
