/**
 * 
 */
package ch16.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author snoopy
 *
 */
public class SelfProtocolURLConnection extends URLConnection {

	protected SelfProtocolURLConnection(URL url) {
		super(url);
	
	}

	
	@Override
	public void connect() throws IOException {


	}

}
