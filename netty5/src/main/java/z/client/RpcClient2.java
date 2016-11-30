/**
 * 
 */
package z.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import z.common.RpcDecoder;
import z.common.RpcEncoder;
import z.common.RpcRequest;
import z.common.RpcResponse;

/**
 * @author Administrator
 *
 */
public class RpcClient2 extends SimpleChannelInboundHandler<RpcResponse> {

    private String host;
    private int port;

    private RpcResponse response;

    // private final Object obj = new Object();

    public RpcClient2(String host, int port) {
        this.host = host;
        
        
        
        
        this.port = port;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        this.response = response;
       System.out.println(response.getResult());
        /*
         * synchronized (obj) { obj.notifyAll(); // 收到响应，唤醒线程 }
         */
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void send(RpcRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline()
                    
                .addLast(new RpcDecoder(RpcResponse.class)) // 将
                                                                    // RPC
                                                                    // 响应进行解码（为了处理响应）
                 .addLast(new RpcEncoder(RpcRequest.class)) // 将
                                                                             // RPC
                                                                             // 请求进行编码（为了发送请求）
                  
                        .addLast(RpcClient2.this); // 使用 RpcClient 发送
                                                   // RPC 请求
            }
        }).option(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture future = bootstrap.connect(host, port).sync();
        future.channel().writeAndFlush(request).sync();

    }

    public static void main(String args[]) throws Exception {
        RpcClient2 client=new RpcClient2("localhost",8080);
        RpcRequest r=new RpcRequest();
        r.setRequestId("100000");
        r.setParameters(new String[]{"testttttttt"});
        client.send(r);
    }
}