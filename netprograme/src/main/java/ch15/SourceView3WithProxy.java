/**
 * 
 */
package ch15;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author snoopy
 * 
 */
public class SourceView3WithProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		usingPorxyClass();
	}

	private static  void usingPorxyClass() {

		try {

			// Open the URLConnection for reading
			URL u = new URL("http://www.baidu.com");
			HttpURLConnection uc ;
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					"172.31.1.246", 80));
			uc =  (HttpURLConnection) u.openConnection(proxy);
			int code = uc.getResponseCode();
			String response = uc.getResponseMessage();
			System.out.println("HTTP/1.x " + code + " " + response);
			for (int j = 1;; j++) {
				String header = uc.getHeaderField(j);
				String key = uc.getHeaderFieldKey(j);
				if (header == null || key == null)
					break;
				// System.out.println(uc.getHeaderFieldKey(j) + ": " + header);

				System.out.println(key + ": " + header);
			} // end for
			System.out.println("");
			InputStream in = new BufferedInputStream(uc.getInputStream());
			// chain the InputStream to a Reader
			Reader r = new InputStreamReader(in);
			int c;
			while ((c = r.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (MalformedURLException ex) {
			System.err.println(" is not a parseable URL");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		// } // end if
	}

	private void setProxyProperties() {
		// TODO Auto-generated method stub

		// for (int i = 0; i < args.length; i++) {
		try {

			System.setProperty("http.proxyHost", "172.31.1.246");
			System.setProperty("http.proxyPort", "80");
			// Open the URLConnection for reading
			URL u = new URL("http://www.baidu.com");
			HttpURLConnection uc = (HttpURLConnection) u.openConnection();
			int code = uc.getResponseCode();
			String response = uc.getResponseMessage();
			System.out.println("HTTP/1.x " + code + " " + response);
			for (int j = 1;; j++) {
				String header = uc.getHeaderField(j);
				String key = uc.getHeaderFieldKey(j);
				if (header == null || key == null)
					break;
				// System.out.println(uc.getHeaderFieldKey(j) + ": " + header);

				System.out.println(key + ": " + header);
			} // end for
			System.out.println("");
			InputStream in = new BufferedInputStream(uc.getInputStream());
			// chain the InputStream to a Reader
			Reader r = new InputStreamReader(in);
			int c;
			while ((c = r.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (MalformedURLException ex) {
			System.err.println(" is not a parseable URL");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		// } // end if
	}
}
