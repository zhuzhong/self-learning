package z.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import z.common.RpcRequest;
import z.common.RpcResponse;

public class RpcProxy {

	private String serverAddress;

	public RpcProxy(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[] { interfaceClass }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
						request.setRequestId(UUID.randomUUID().toString());
						request.setClassName(method.getDeclaringClass()
								.getName());
						request.setMethodName(method.getName());
						request.setParameterTypes(method.getParameterTypes());
						request.setParameters(args);

						String[] array = serverAddress.split(":");
						String host = array[0];
						int port = Integer.parseInt(array[1]);

						RpcClient client = new RpcClient(host, port); // 初始化 RPC
																		// 客户端
						RpcResponse response = client.send(request); // 通过 RPC
																		// 客户端发送
																		// RPC
																		// 请求并获取
																		// RPC
																		// 响应

						if (response.isError()) {
							throw response.getError();
						} else {
							return response.getResult();
						}
					}
				});
	}
}