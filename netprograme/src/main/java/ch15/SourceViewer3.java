package ch15;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer3 {

	public static void main(String[] args) {

		// for (int i = 0; i < args.length; i++) {
		try {

			// Open the URLConnection for reading
			URL u = new URL("http://files.laitec.ir/wp-content/uploads/2014/01/Oreilly.Java_.RMI_.Oct_.2001.pdf");
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
//			while ((c = r.read()) != -1) {
//				System.out.print((char) c);
//			}
		} catch (MalformedURLException ex) {
			System.err.println(args[0] + " is not a parseable URL");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		// } // end if

	} // end main

} // end SourceViewer3
