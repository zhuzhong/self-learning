package z.server.demo2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import z.common.RpcRequest;
import z.common.RpcResponse;

public class RpcHandler2 extends SimpleChannelInboundHandler<RpcRequest> {

    private final Map<String, Object> handlerMap;

    public RpcHandler2(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    private volatile Channel channel;

    @Override
    public void messageReceived(final ChannelHandlerContext ctx, RpcRequest request) throws Exception {

        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object result = handle(request);
            response.setResult(result);
        } catch (Throwable t) {
            response.setError(t);
        }
        ctx.writeAndFlush(response);
        ///.addListener(ChannelFutureListener.CLOSE);
        channel = ctx.channel();
        sendMessageThread();
    }
/**
 *  持有了channel可以定期向客户端推送消息
 */
    private void sendMessageThread() {
        new Thread() {
            public void run() {
                while (channel.isWritable()) {
                    try {
                      
                        RpcResponse response = new RpcResponse();
                        response.setRequestId("1");
                        System.out.println(Thread.currentThread().getId()+"send message...."+channel.isActive()+"-"+channel.isOpen()+"-"+channel.isRegistered()+"- "+channel.isWritable());
                       /* if(!channel.isWritable()){
                        	System.out.println("send message thread died..."+Thread.currentThread().getId());
                        	break;
                        }*/
                        response.setResult("from server message....");
                        channel.writeAndFlush(response);
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private Object handle(RpcRequest request) throws Throwable {
        /*
         * String className = request.getClassName(); Object serviceBean =
         * handlerMap.get(className);
         * 
         * Class<?> serviceClass = serviceBean.getClass(); String methodName =
         * request.getMethodName(); Class<?>[] parameterTypes =
         * request.getParameterTypes(); Object[] parameters =
         * request.getParameters();
         * 
         * Method method = serviceClass.getMethod(methodName, parameterTypes);
         * method.setAccessible(true); return method.invoke(serviceBean,
         * parameters);
         */
        System.out.println("request id is " + request.getRequestId());
        System.out.println("请求能数=" + request.getParameters()[0]);
        return "服务器会每隔5秒发送一条消息给你，注意查收哦!";

        /*
         * FastClass serviceFastClass = FastClass.create(serviceClass);
         * FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName,
         * parameterTypes); return serviceFastMethod.invoke(serviceBean,
         * parameters);
         */
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}