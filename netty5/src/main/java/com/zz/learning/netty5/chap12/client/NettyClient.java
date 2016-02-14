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
package com.zz.learning.netty5.chap12.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
public class NettyClient {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception {

        // 配置客户端NIO线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024));
                            // ch.pipeline().addLast(new
                            // NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthHandler", new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());
                            ch.pipeline().addLast("businessMessageHandler", new BusinessMessageReqHandler());
                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host, port),
                    new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
            channel = future.channel();
            channel.closeFuture().sync();
            // future.channel().closeFuture().sync();
            // return future.channel();
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作

            /*
             * executor.execute(new Runnable() {
             * 
             * @Override public void run() { try { TimeUnit.SECONDS.sleep(1);
             * try { if(channel==null||!channel.isActive())
             * connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作 }
             * catch (Exception e) { e.printStackTrace(); } } catch
             * (InterruptedException e) { e.printStackTrace(); } } });
             */

        }
    }

    // private static BusinessMessageReqHandler handler = new
    // BusinessMessageReqHandler();

    private Channel channel;

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                } catch (Exception e) {

                    e.printStackTrace();
                    // executor.execute(new Runnable() {
                    //
                    // @Override
                    // public void run() {
                    // try {
                    // TimeUnit.SECONDS.sleep(1);
                    // try {
                    // if(channel==null||!channel.isActive())
                    // connect(NettyConstant.PORT, NettyConstant.REMOTEIP);//
                    // 发起重连操作
                    // } catch (Exception e) {
                    // e.printStackTrace();
                    // }
                    // } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // }
                    // }
                    // });
                } finally {
                    executor.execute(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                                try {
                                    if (channel == null || !channel.isActive())
                                        connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        }).start();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient();
        // client.connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
        client.init();
        TimeUnit.SECONDS.sleep(10);
        for (int i = 0; i < 10; i++) {
            client.sendMessage();
            TimeUnit.SECONDS.sleep(i * 5);
        }
        // handler.sendMessage(client.buildHeatBeat("from client test------------------------"));

    }

    private void sendMessage() {
        channel.writeAndFlush(buildHeatBeat("from client test------------------------"));
    }

    private NettyMessage buildHeatBeat(String body) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SERVICE_REQ.value());
        message.setHeader(header);
        message.setBody(body);
        return message;
    }
}
