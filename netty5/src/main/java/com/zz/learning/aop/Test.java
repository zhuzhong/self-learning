/**
 * 
 */
package com.zz.learning.aop;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BeanFactory bf=new ClassPathXmlApplicationContext("/com/zz/learning/aop/spring-aop.xml");
        Iplay play=(Iplay)bf.getBean("playService");
        play.playService("pingpong");
    }

}
