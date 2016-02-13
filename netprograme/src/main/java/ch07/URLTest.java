/**
 * 
 */
package ch07;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author snoopy
 *
 */
public class URLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	try {
		URL u=new URL("ftp://anymous:anymous@www.baidu.com");
//		System.out.println(u.getHost()+" "+u.getPort()+" "+u.getDefaultPort());
//	u=new URL("http://zhu:test@www.baidu.com/mytest/index.php?category=Piano");
//	System.out.println(u.getPath());
//	System.out.println(u.getFile());
//	System.out.println(u.getQuery());
//	System.out.println(u.getUserInfo());
//	System.out.println(u.getAuthority());
	
	
	u=new URL("http://www.ceair.com");
	Reader in=new InputStreamReader(new BufferedInputStream(u.openStream()));
	int c;
	while((c=in.read())!=-1){
		//System.out.println(c);
		 System.out.print((char) c);
	}
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
