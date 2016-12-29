package com.zz.learning.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileProcessWithCamel {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {

            public void configure() {
                FileConvertProcessor processor = new FileConvertProcessor();
                from("file:d:/temp/inbox?noop=true").process(processor).to("file:d:/temp/outbox");
            }
        });

        context.start();
        boolean loop = true;
        while (loop) {
            Thread.sleep(25000);
        }
        context.stop();
    }
}