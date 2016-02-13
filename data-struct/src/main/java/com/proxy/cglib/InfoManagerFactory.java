package com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class InfoManagerFactory {
    private static InfoManager manger = new InfoManager();
    /**
     * 创建原始的InfoManager
     * 
     * @return
     */
    public static InfoManager getInstance() {
        return manger;
    }
    
    
    /**
     * 创建带有权限检验的InfoManager
     * 
     * @param auth
     * @return
     */
    public static InfoManager getAuthInstance(AuthProxy auth) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(InfoManager.class);
        enhancer.setCallback(auth);
        return (InfoManager) enhancer.create();
    }
}