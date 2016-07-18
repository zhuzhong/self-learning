package z.api.support;

import z.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! dddddddddddddddddddddddddddd" + name;
    }
}