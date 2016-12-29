package com.zz.learning.aop;

import org.springframework.aop.ClassFilter;

class PlayClassFilter implements ClassFilter {
    public boolean matches(Class clazz) {
        if (clazz.getSimpleName().equals("Play"))
            return true;
        return false;
    }
}