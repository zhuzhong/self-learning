package ch15;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BinarySaver {

	public static void main(String args[]) {

		// for (int i = 0; i < args.length; i++) {

		try {
			URL root = new URL(
					"http://files.laitec.ir/wp-content/uploads/2014/01/Oreilly.Java_.RMI_.Oct_.2001.pdf");
			saveBinaryFile(root);
		} catch (MalformedURLException ex) {
			System.err.println(" it is not URL I understand.");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		// } // end for

	} // end main

	public static void saveBinaryFile(URL u) throws IOException {

		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		if (contentType.startsWith("text/") || contentLength == -1) {
			throw new IOException("This is not a binary file.");
		}

		InputStream raw = uc.getInputStream();
		InputStream in = new BufferedInputStream(raw);
		byte[] data = new byte[contentLength];
		int bytesRead = 0;
		int offset = 0;
		
		while (offset < contentLength) {
			bytesRead = in.read(data, offset, data.length - offset);
		
			if (bytesRead == -1)
				break;
			offset += bytesRead;
			System.out.println("contentlength="+contentLength);
			System.out.println("this is time read="+bytesRead);
			System.out.println("total read="+offset);
		}
		in.close();

		if (offset != contentLength) {
			throw new IOException("Only read " + offset + " bytes; Expected "
					+ contentLength + " bytes");
		}

		String filename = u.getFile();
		filename = filename.substring(filename.lastIndexOf('/') + 1);
		FileOutputStream fout = new FileOutputStream(filename);
		fout.write(data);
		fout.flush();
		fout.close();
System.out.println("this is end...");
	}

} // end BinarySaver
