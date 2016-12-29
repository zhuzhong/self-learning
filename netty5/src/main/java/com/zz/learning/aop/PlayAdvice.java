package com.zz.learning.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class PlayAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("my before advice");
        // method.invoke(target, args); 如果再调用这句，则目标方法会执行多一次
    }
}