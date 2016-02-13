package com.behaviour.mediator;
//http://www.cnblogs.com/java-my-life/archive/2012/06/20/2554024.html
public interface Mediator {
    /**
     * 同事对象在自身改变的时候来通知调停者方法
     * 让调停者去负责相应的与其他同事对象的交互
     */
    public void changed(Colleague c);
}