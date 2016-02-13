/**
 * 
 */
package com.zz.learning.netty5.chap12;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;

import io.netty.buffer.ByteBuf;

/**
 * @author sunff
 *
 */
public class ChannelBufferByteOutput implements ByteOutput {

    private ByteBuf out;

    /**
     * @param out
     */
    public ChannelBufferByteOutput(ByteBuf out) {
        this.out = out;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.Closeable#close()
     */
    public void close() throws IOException {


    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.Flushable#flush()
     */
    public void flush() throws IOException {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.marshalling.ByteOutput#write(int)
     */
    public void write(int b) throws IOException {


    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.marshalling.ByteOutput#write(byte[])
     */
    public void write(byte[] b) throws IOException {


    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.marshalling.ByteOutput#write(byte[], int, int)
     */
    public void write(byte[] b, int off, int len) throws IOException {


    }

}
