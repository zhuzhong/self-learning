/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sunff
 *
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();

    private String[] whiteList = { "127.0.0.1", "192.168.1.104" };
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message=(NettyMessage)msg;
        if(message.getHeader()!=null
                &&message.getHeader().getType()==MessageType.LOGIN_REQ.value()
                ){
            String nodeIndex=ctx.channel().remoteAddress().toString();
            NettyMessage loginResp=null;
            
            //重复登陆，拒绝
            if(nodeCheck.containsKey(nodeIndex)){
                loginResp=buildResponse((byte)-1);
            }
        }
        
    }
    /**
     * @param b
     * @return
     */
    private NettyMessage buildResponse(byte b) {
        // TODO Auto-generated method stub
        return null;
    }
}
