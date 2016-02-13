package ch18.rmi.client;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch18.rmi.IHelloWorld;

public class HelloClient {  
  
    public static void main(String[] args) {  
    	test1();
        
    }  
 
  
    private void test2(){
    	//这样不行，也就是用spring发布的rmi程序，用标准的客户端是调用不行的
    	 try {
 			Object o = Naming.lookup("rmi://172.20.241.105:8088/hello");
 			IHelloWorld hs=(IHelloWorld)o;
 			System.out.println(hs.helloWorld());  
 	        System.out.println(hs.sayHelloToSomeBody("Lavasoft"));
 			
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (RemoteException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (NotBoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    private static  void test1(){
    	//直接应用spring的配置
    	   ApplicationContext ctx = new ClassPathXmlApplicationContext(  
                   "ch18/rmi/client/rmi_client_context.xml");  
           IHelloWorld hs = (IHelloWorld) ctx.getBean("helloWorld");  
           System.out.println(hs.helloWorld());  
           System.out.println(hs.sayHelloToSomeBody("Lavasoft"));  
           javax.sql.DataSource d=(javax.sql.DataSource)ctx.getBean("csmDataSource");
         try {
			Connection c=  d.getConnection();
			PreparedStatement	p =c.prepareStatement("SELECT t.user_id FROM rabc_user t WHERE t.user_id=1");
			
	     ResultSet   rs = p.executeQuery(); 
	       // ArrayList<TableVo> tVoList = new ArrayList<TableVo>(); 
	        while (rs.next()) { 
	           System.out.println(rs.getInt(1)) ;
	        } 
	        rs.close();
	        p.close();
	        c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}  