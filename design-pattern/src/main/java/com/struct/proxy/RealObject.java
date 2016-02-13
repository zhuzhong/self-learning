package com.struct.proxy;
public class RealObject extends AbstractObject {
    @Override
    public void operation() {
        //一些操作
        System.out.println("这些操作来自真实实例");
    }
}