/**
 * 
 */
package z.client;

import z.api.HelloService;

/**
 * @author sunff
 *
 */
public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RpcProxy p=new RpcProxy("localhost:8080");
		HelloService h=p.create(HelloService.class);
		System.out.println(h.hello("zhuzhong"));

	}

}
