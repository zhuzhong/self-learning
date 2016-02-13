package ch18.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class HelloHost {  
    public static void main(String[] args) {  
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ch18/rmi/rmi_server_context.xml");  
        System.out.println("RMI服务伴随Spring的启动而启动了.....");  
    }  
}  