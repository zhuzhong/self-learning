/**
 * 
 */
package z.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import z.annotation.RpcService;
import z.common.RpcDecoder;
import z.common.RpcEncoder;
import z.common.RpcRequest;
import z.common.RpcResponse;

/**
 * @author Administrator
 *
 */
public class SpringRpcServer implements ApplicationContextAware, InitializingBean {

    private String serverAddress;

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new NettyThread().start();
    }

    private class NettyThread extends Thread {
        @Override
        public void run() {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {

                            @Override
                            protected void initChannel(SocketChannel channel) throws Exception {
                                channel.pipeline().addLast(new RpcDecoder(RpcRequest.class)) // 将
                                                                                             // RPC
                                                                                             // 请求进行解码（为了处理请求）
                                        .addLast(new RpcEncoder(RpcResponse.class)) // 将
                                                                                    // RPC
                                                                                    // 响应进行编码（为了返回响应）
                                        .addLast(new RpcHandler(handlerMap)); // 处理
                                                                              // RPC
                                                                              // 请求

                            }

                        }).option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, true);
                String[] array = serverAddress.split(":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);

                ChannelFuture future = bootstrap.bind(host, port).sync();

                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("netty server 启动失败", e);
            }
            finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }

    }

    private Map<String, Object> handlerMap = new HashMap<>(); // 存放接口名与服务对象之间的映射关系

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (serviceBeanMap != null && !serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                Set<Class<?>> interfaces = getRpcServiceInterfaces(serviceBean.getClass());
                for (Class<?> interfase : interfaces) {
                    handlerMap.put(interfase.getName(), serviceBean);
                }
            }
        }

    }

    private Set<Class<?>> getRpcServiceInterfaces(Class<?> clazz) throws IllegalArgumentException {
        Set<Class<?>> openables = new HashSet<Class<?>>();
        if (clazz.isAnnotation() || clazz.isEnum()) {
            throw new IllegalArgumentException();
        }
        if (clazz.isInterface()) {
            if (clazz.isAnnotationPresent(RpcService.class)) {
                openables.add(clazz);
            }
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> _interface : interfaces) {
                openables.addAll(getRpcServiceInterfaces(_interface));
            }
            return openables;
        } else {
            if (clazz == Object.class) {
                return openables;
            }
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> interfase : interfaces) {
                openables.addAll(getRpcServiceInterfaces(interfase));
            }
            openables.addAll(getRpcServiceInterfaces(clazz.getSuperclass()));
            return openables;
        }
    }

}
