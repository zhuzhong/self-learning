/**
 * 
 */
package com.zz.learning.cache;

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

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/com/zz/learning/cache/spring-cache-2.xml");

        DemoService demoServie = (DemoService) context.getBean("demoServiceImpl");
        for (int i = 0; i < 10; i++)
            System.out.println(demoServie.getStrByName("test"));

    }
}
