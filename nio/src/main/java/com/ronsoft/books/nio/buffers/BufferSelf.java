package com.ronsoft.books.nio.buffers;

import java.nio.CharBuffer;

public class BufferSelf {
    public static void main(String[] argv) throws Exception {
        CharBuffer cb = CharBuffer.allocate(100);

        cb.put("This is a test String");

        cb.flip();
       /* cb.equals(ob)
        cb.compareTo(that)*/

        // This throws an IllegalArgumentException
        cb.put(cb);

        System.out.println(cb);
    }
}
