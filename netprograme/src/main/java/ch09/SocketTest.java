/**
 * 
 */
package ch09;

import java.net.Socket;
import java.net.SocketException;

/**
 * @author snoopy
 *
 */
public class SocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket so=new Socket();
		try {
			so.setTrafficClass(2);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		so.setPerformancePreferences(2, 1, 3);

	}

}
