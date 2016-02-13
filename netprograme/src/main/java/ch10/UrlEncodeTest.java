/**
 * 
 */
package ch10;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author snoopy
 *
 */
public class UrlEncodeTest {

	
	public static void main(String [] args){
		URLDecoder d=new URLDecoder();
	String s;
	try {
		s = d.decode("http://ssov2.ceair.com/?ActionUrl=http%3a%2f%2fwebparts.ceair.com%2fSSO%2fRedirect.aspx%3fAppCode%3dEoffice%26ReturnUrl%3dhttp%3a%2f%2flceoffice.ceair.com%2fweboa%2fgw%2fDShouwen.nsf%2f0%2f17BC964BA44F0A1D48257CC200329BE5%3fopendocument", "utf-8");
		System.out.println(s);
		} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
