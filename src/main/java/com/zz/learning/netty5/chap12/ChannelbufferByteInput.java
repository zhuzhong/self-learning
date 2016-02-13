/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;

/**
 * @author sunff
 *
 */
public class ChannelbufferByteInput implements ByteInput {

    /**
     * @param buf
     */
    public ChannelbufferByteInput(ByteBuf buf) {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see java.io.Closeable#close()
     */
    public void close() throws IOException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.jboss.marshalling.ByteInput#read()
     */
    public int read() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.jboss.marshalling.ByteInput#read(byte[])
     */
    public int read(byte[] b) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.jboss.marshalling.ByteInput#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.jboss.marshalling.ByteInput#available()
     */
    public int available() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.jboss.marshalling.ByteInput#skip(long)
     */
    public long skip(long n) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

}
