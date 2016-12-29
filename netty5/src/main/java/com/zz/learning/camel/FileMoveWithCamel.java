package com.zz.learning.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileMoveWithCamel {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                System.out.println("1111");
                // from("file:d:/temp/inbox?noop=true").to("file:d:/temp/outbox");
                from("file:D:/tmp/Project/async-gen/?delay=3000").to("file:d:/tmp/test");
            }
        });
        context.start();
        boolean loop = true;
        while (loop) {
            Thread.sleep(5000);
            System.out.println("222");
        }
        context.stop();
        
        System.out.println("00000ook");
        
       
    }
}
