package ch16;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.macfaq.net.www.protocol.NewFactory;

public class SourceViewer4 {
	public static void main(String[] args) {

		URL.setURLStreamHandlerFactory(new NewFactory());

		if (args.length > 0) {
			try {
				// Open the URL for reading
				URL u = new URL(args[0]);
				InputStream in = new BufferedInputStream(u.openStream());
				// chain the InputStream to a Reader
				Reader r = new InputStreamReader(in);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char) c);
				}
			} catch (MalformedURLException ex) {
				System.err.println(args[0] + " is not a parseable URL");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		} // end if
	} // end main
} // end SourceViewer3
