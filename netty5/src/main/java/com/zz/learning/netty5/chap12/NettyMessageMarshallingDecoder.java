/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

/**
 * @author sunff
 *
 */
public class NettyMessageMarshallingDecoder {

    private final Unmarshaller unmarshaller;

    public NettyMessageMarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodecFactory.buildUnMarshalling();

    }

    /**
     * @param in
     * @return
     */
    public Object decode(ByteBuf in) throws Exception {
        int objectSize = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(), objectSize);
        ByteInput input = new ChannelbufferByteInput(buf);
        try {
            unmarshaller.start(input);
            Object obj = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex() + objectSize);

            return obj;
        } finally {
            unmarshaller.close();
        }
       // return null;
    }

}
