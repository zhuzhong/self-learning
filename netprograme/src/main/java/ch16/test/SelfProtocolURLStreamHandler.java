/**
 * 
 */
package ch16.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @author snoopy
 *
 */
public class SelfProtocolURLStreamHandler extends URLStreamHandler {

	
	@Override
	protected URLConnection openConnection(URL u) throws IOException {

		return new SelfProtocolURLConnection(u);
	}

}
