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
		String url=null;
		/*="https://github.com/alibaba/canal/releases/download/canal-1.0.19/canal.deployer-1.0.19.tar.gz";
		url="https://github-cloud.s3.amazonaws.com/releases/26206274/fc280436-d6c5-11e4-9cfe-13d62129bba9.gz?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAISTNZFOVBIJMK3TQ%2F20160321%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20160321T120429Z&X-Amz-Expires=300&X-Amz-Signature=c2134518fbd59832dcb527b670fac105131c19c998d92f70430eb3103cd0d719&X-Amz-SignedHeaders=host&actor_id=15956780&response-content-disposition=attachment%3B%20filename%3Dalibaba-rocketmq-3.2.6.tar.gz&response-content-type=application%2Foctet-stream";
		
		url="https://github.com/alibaba/RocketMQ/releases/download/v3.2.6/alibaba-rocketmq-3.2.6.tar.gz";
		url="https://github-cloud.s3.amazonaws.com/releases/26206274/fc280436-d6c5-11e4-9cfe-13d62129bba9.gz?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAISTNZFOVBIJMK3TQ%2F20160321%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20160321T122137Z&X-Amz-Expires=300&X-Amz-Signature=663bdf10a0a10f4d17bbb57dbccdc03eec58da553d381b94a7b0529f2b7baeb5&X-Amz-SignedHeaders=host&actor_id=15956780&response-content-disposition=attachment%3B%20filename%3Dalibaba-rocketmq-3.2.6.tar.gz&response-content-type=application%2Foctet-stream";
		url="http://cdn.mysql.com//Downloads/MySQL-5.7/mysql-5.7.11-win32.zip";
		url="https://downloads.gradle.org/distributions/gradle-1.8-bin.zip";*/
		url="https://download3.vmware.com/software/wkst/file/VMware-Workstation-Full-10.0.1-1379776.i386.bundle";
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
		FileOutputStream fout = new FileOutputStream("C:/Users/sunff/Desktop/test2/Full-10.0.1-1379776.i386.bundle");
		fout.write(data);
		fout.flush();
		fout.close();
		System.out.println("this is download end...");
	}

}
