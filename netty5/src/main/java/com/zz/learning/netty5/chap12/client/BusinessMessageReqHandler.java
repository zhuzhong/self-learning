package com.zz.learning.netty5.chap12.client;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.zz.learning.netty5.chap12.MessageType;
import com.zz.learning.netty5.chap12.struct.NettyMessage;




public class BusinessMessageReqHandler extends ChannelHandlerAdapter {

    // private volatile NettyMessage message;

    private ChannelHandlerContext ctx; // 把它拿出来

    public void sendMessage(NettyMessage msg) {
        if (ctx == null) {
            throw new RuntimeException("in BusinessMessageReqHandler ctx property is null...");
        }
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 握手成功，主动发送心跳消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            // 发送业务请求消息
            this.ctx = ctx;
        } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.SERVICE_RESP.value()) {
            System.out.println("&&&&&&&&&&&&&&&&&&Client receive server business message : ---> " + message);
        } else {
            ctx.fireChannelRead(msg); // 处理不了的情况
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        ctx.fireExceptionCaught(cause);
    }

}
