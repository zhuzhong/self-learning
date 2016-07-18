/**
 * 
 */
package com.j1.client.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.j1.server.service.HelloService;

/**
 * @author Administrator
 *
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @Audit
    public String hello() {
        return "hello";
    }

    @Autowired
    private HelloService helloService;

    @Autowired
    private ConfigService configService;

    @RequestMapping("sayHello")
    @Audit
    public String sayHello(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");

        try {
            PrintWriter pw = response.getWriter();
            for (int i = 0; i < 100; i++) {
                String result = helloService.sayHello(name);
                pw.write(result);
            }
            String result = configService.hello(name);
            pw.write(result);
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "success";
    }
}
