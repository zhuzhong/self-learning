package z.server.demo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;
import java.util.Map;

import z.common.RpcDecoder;
import z.common.RpcEncoder;
import z.common.RpcRequest;
import z.common.RpcResponse;

/**
 * @author sunff
 *
 */
public class RpcServer2 {

	private String serverAddress;

	public RpcServer2(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RpcServer2 r = new RpcServer2("localhost:8080");

		r.init();

	}

	private Map<String, Object> handlerMap = new HashMap<>(); // 存放接口名与服务对象之间的映射关系

	public void init() {
		initHandlerMap();
		try {
			initNetty();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initHandlerMap() {
		//handlerMap.put(HelloService.class.getName(), new HelloServiceImpl());
	}

	private void initNetty() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel channel)
								throws Exception {
							channel.pipeline()
									.addLast(new RpcDecoder(RpcRequest.class)) // 将
																				// RPC
																				// 请求进行解码（为了处理请求）
									.addLast(new RpcEncoder(RpcResponse.class)) // 将
																				// RPC
																				// 响应进行编码（为了返回响应）
									.addLast(new RpcHandler2(handlerMap)); // 处理
																			// RPC
																			// 请求

						}

					}).option(ChannelOption.SO_BACKLOG, 1024)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			String[] array = serverAddress.split(":");
			String host = array[0];
			int port = Integer.parseInt(array[1]);

			ChannelFuture future = bootstrap.bind(host, port).sync();

			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
