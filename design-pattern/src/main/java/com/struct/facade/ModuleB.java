package com.struct.facade;
public class ModuleB {
    /**
     * 提供给子系统外部使用的方法
     */
    public void b1(){};
    
    /**
     * 子系统内部模块之间相互调用时使用的方法
     */
    public void b2(){};
    public void b3(){};
}