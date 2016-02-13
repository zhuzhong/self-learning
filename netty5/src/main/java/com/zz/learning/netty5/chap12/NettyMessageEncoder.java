/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author sunff
 *
 */
public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    private NettyMessageMarshallingEncoder marshallingEncoder;

    /**
     * @param marshallingEncoder
     */
    public NettyMessageEncoder() throws IOException {

        this.marshallingEncoder = new NettyMessageMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("the encode message is null");
        }
        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionID());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeInt(msg.getHeader().getAttachement().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachement().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("utf-8");

            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }

        key = null;

        keyArray = null;
        value = null;

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);

        } else {
            sendBuf.writeInt(0); // 没有消息体
        }

        // 更新消息头
        sendBuf.setInt(4, sendBuf.readableBytes());
    }

}
