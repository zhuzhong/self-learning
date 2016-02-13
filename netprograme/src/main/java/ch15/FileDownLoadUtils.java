/**
 * 
 */
package ch15;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class FileDownLoadUtils {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//String url="http://172.20.35.37:7080/esb/QueryPassengerInfoByFDDA?wsdl";
		String url="https://github.com/alibaba/canal/releases/download/canal-1.0.19/canal.deployer-1.0.19.tar.gz";
		downLoadWhatYouWant(url);
	} // end main

	public static void downLoadWhatYouWant(String urlPath) throws IOException {
		URL u = null;
		try {
			u = new URL(urlPath);

		} catch (MalformedURLException ex) {
			System.err.println(" it is not URL I understand.");
			return;
		} 
		boolean gongsi=false;
		URLConnection uc;
		if(gongsi){
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					"172.31.1.246", 80));
			//uc =  (HttpURLConnection) u.openConnection(proxy);
			
			 uc = u.openConnection(proxy);
		}else{
			uc=u.openConnection();
		}
	
		//String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
//		if (contentType.startsWith("text/") || contentLength == -1) {
//			throw new IOException("This is not a binary file.");
//		}

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
			System.out.println("contentlength=" + contentLength);
			System.out.println("this is time read=" + bytesRead);
			System.out.println("total read=" + offset);
		}
		in.close();

		if (offset != contentLength) {
			throw new IOException("Only read " + offset + " bytes; Expected "
					+ contentLength + " bytes");
		}

		String filename = u.getFile();
		filename = filename.substring(filename.lastIndexOf('/') + 1);
		FileOutputStream fout = new FileOutputStream("canal.deployer-1.0.19.tar.gz");
		fout.write(data);
		fout.flush();
		fout.close();
		System.out.println("this is download end...");
	}

}
