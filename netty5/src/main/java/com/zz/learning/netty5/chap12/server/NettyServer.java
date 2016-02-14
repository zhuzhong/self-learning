/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zz.learning.netty5.chap12.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.IOException;
import java.sql.Time;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.zz.learning.netty5.chap12.MessageType;
import com.zz.learning.netty5.chap12.NettyConstant;
import com.zz.learning.netty5.chap12.codec.NettyMessageDecoder;
import com.zz.learning.netty5.chap12.codec.NettyMessageEncoder;
import com.zz.learning.netty5.chap12.struct.Header;
import com.zz.learning.netty5.chap12.struct.NettyMessage;

/**
 * @author Lilinfeng
 * @date 2014年3月15日
 * @version 1.0
 */
public class NettyServer {

    private void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws IOException {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024));
                        // ch.pipeline().addLast(new NettyMessageDecoder(1024 *
                        // 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                        ch.pipeline().addLast(new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                        ch.pipeline().addLast("BusinessMessageRespHandler", new BusinessMessageRespHandler());
                    }
                });

        // 绑定端口，同步等待成功
        ChannelFuture cf = b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
      //  serverChannel = cf.channel();
        System.out.println("Netty server start ok : " + (NettyConstant.REMOTEIP + " : " + NettyConstant.PORT));
    }

   // private Channel serverChannel;

    
    private void init(){
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    bind();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        }).start();
    }
    public static void main(String[] args) throws Exception {
       NettyServer ns= new NettyServer();
       ns.init();
       TimeUnit.SECONDS.sleep(120);
       ns.tuiMessage();
    }
    
    private void tuiMessage(){
        //this.serverChannel.writeAndFlush(buildHeatBeat());
    	for(Map.Entry<String, Channel> client:LoginAuthRespHandler.clientChannels.entrySet()){
    		client.getValue().writeAndFlush(buildHeatBeat());
    	}
    }
    
    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SERVICE_RESP.value());
        message.setHeader(header);
        message.setBody("tuittttttttttttttttttttttttttt");
        return message;
    }
    
}
