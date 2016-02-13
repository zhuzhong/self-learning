package com.struct.facade;
//http://www.cnblogs.com/java-my-life/archive/2012/05/02/2478101.html
//tomcat中用到了门面模式
public class ModuleFacade {
    
    Module a = new Module();
    ModuleB b = new ModuleB();
    ModuleC c = new ModuleC();
    /**
     * 下面这些是A、B、C模块对子系统外部提供的方法
     */
    public void a1(){
        a.a1();
    }
    public void b1(){
        b.b1();
    }
    public void c1(){
        c.c1();
    }
}