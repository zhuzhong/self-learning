/**
 * 
 */
package com.zz.learning.netty5.chap12;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author sunff
 *
 */
public class NettyServer {

    public void bind() throws Exception{
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup, workerGroup);
    }
}
