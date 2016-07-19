/**
 * 
 */
package z.server;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class ServerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:z/server/spring.xml");
        System.out.println("init spring server...");
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
