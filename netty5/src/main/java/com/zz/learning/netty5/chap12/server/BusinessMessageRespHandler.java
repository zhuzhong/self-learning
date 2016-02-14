/**
 * 
 */
package com.zz.learning.netty5.chap12.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.zz.learning.netty5.chap12.MessageType;
import com.zz.learning.netty5.chap12.struct.Header;
import com.zz.learning.netty5.chap12.struct.NettyMessage;

/**
 * @author sunff
 *
 */
public class BusinessMessageRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 返回心跳应答消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.SERVICE_REQ.value()) {
            System.out.println("&&&&&&&&&Receive client business service message : ---> " + message);
            NettyMessage heartBeat = buildHeatBeat();
            System.out.println("&&&&&&&&Send service response business message to client : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SERVICE_RESP.value());
        message.setHeader(header);
        message.setBody("from server business message");
        return message;
    }
}
