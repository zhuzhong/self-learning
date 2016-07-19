package z.api;

import z.annotation.RpcService;

@RpcService
public interface HelloService {

    String hello(String name);
}