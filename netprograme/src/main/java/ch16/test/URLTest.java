/**
 * 
 */
package ch16.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandlerFactory;

/**
 * 
 * url解析的一般过程，通过url类的构造器，可以获取相应资源的地址，
 * 然后设置需要解析此资源的协议处理器工厂实例，
 * 而这个工厂实例，则可以生一个对应的协议的流处理器StreamHandler,
 * 这个流处理器，即根据数据流的内容而产生相应的urlConnection实例，
 * 最终用户操纵这个urlconnection实例与最初的url资源进行交互
 * @author snoopy
 *
 */
public class URLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			URLStreamHandlerFactory fatory=null;
		
			URL url=new URL("http://www.baidu.com");
			url.setURLStreamHandlerFactory(fatory);
			//url.openConnection();
			System.out.println(System.getProperty("java.protocol.handler.pkgs"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
