/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;



/**
 * @author sunff
 *
 */
public class NettyMessageMarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private Marshaller marshaller;

    public NettyMessageMarshallingEncoder() throws IOException {
        this.marshaller = MarshallingCodecFactory.buildMarshalling();
    }

    /**
     * @param value
     * @param sendBuf
     */
    protected void encode(Object msg, ByteBuf out) throws Exception {
        try {
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } finally {
            marshaller.close();
        }

    }

}
