/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author sunff
 *
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            Object lr = message.getBody();
            /*
             * if(lr instanceof byte){
             * 
             * }
             */
            System.out.println("这里写的与书上不一样...");
            byte loginResult = (Byte) lr;
            if (loginResult != (byte) 0) {
                ctx.close();// 握手失败
            } else {
                System.out.println("Login is ok:" + message);

                ctx.fireChannelRead(msg);
            }

        } else {
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * @return
     */
    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
