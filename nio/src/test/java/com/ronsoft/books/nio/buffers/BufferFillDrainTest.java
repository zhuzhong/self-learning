/**
 * 
 */
package com.ronsoft.books.nio.buffers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.CharBuffer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sunff
 *
 */
public class BufferFillDrainTest {

    CharBuffer buffer = null;
    BufferFillDrain fd;

    @Before
    public void init() {
        buffer = CharBuffer.allocate(100);
    }

    @Test
    public void fillBuffer() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        fd = new BufferFillDrain();
        Method fillBufferMethod = BufferFillDrain.class.getDeclaredMethod("fillBuffer", CharBuffer.class);
        fillBufferMethod.setAccessible(true);
        Object filled = fillBufferMethod.invoke(fd, buffer);
        Assert.assertTrue((Boolean) filled);
    }
   
    @Test
    public void drainBuffer() throws Exception{
        fillBuffer();
        buffer.flip();
        Method drainBufferMethod=BufferFillDrain.class.getDeclaredMethod("drainBuffer", CharBuffer.class);
        drainBufferMethod.setAccessible(true);
        drainBufferMethod.invoke(fd, buffer);
    }
    
    
}
