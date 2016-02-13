/**
 * 
 */
package ch16.test;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * @author snoopy
 * 
 */
public class URLStreamHandlerFactoryImpl implements URLStreamHandlerFactory {

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if (protocol.equals("self"))
			return new SelfProtocolURLStreamHandler();
		else
			return null;
	}

}
