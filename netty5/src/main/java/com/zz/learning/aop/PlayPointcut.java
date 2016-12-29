package com.zz.learning.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

public class PlayPointcut implements Pointcut {
    public ClassFilter getClassFilter() {
        return new PlayClassFilter();
    }

    public MethodMatcher getMethodMatcher() {
        return new PlayMethodMatcher();
    }
}