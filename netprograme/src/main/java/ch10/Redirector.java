package ch10;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Redirector implements Runnable {

	private int port;
	private String newSite;

	public Redirector(String site, int port) {
		this.port = port;
		this.newSite = site;
	}

	public void run() {

		try {

			ServerSocket server = new ServerSocket(this.port);
			System.out.println("Redirecting connections on port "
					+ server.getLocalPort() + " to " + newSite);

			while (true) {
				try {
					Socket s = server.accept();
					Thread t = new RedirectThread(s,newSite);
					t.start();
				} // end try
				catch (IOException ex) {
				}

			} // end while

		} // end try
		catch (BindException ex) {
			System.err.println("Could not start server. Port Occupied");
		} catch (IOException ex) {
			System.err.println(ex);
		}

	} // end run

	static class RedirectThread extends Thread {

		private Socket connection;
		private String newSite;

		RedirectThread(Socket s,String newSite) {
			this.connection = s;
			this.newSite=newSite;
		}

		public void run() {

			try {

				Writer out = new BufferedWriter(new OutputStreamWriter(
						connection.getOutputStream(), "ASCII"));
				Reader in = new InputStreamReader(new BufferedInputStream(
						connection.getInputStream()));

				// read the first line only; that's all we need  只读取http报文的第一行
				//第一行的内容类似 POST /index.html HTTP/1.0
				StringBuffer request = new StringBuffer(80);
				while (true) {
					int c = in.read();
					if (c == '\r' || c == '\n' || c == -1)
						break;
					request.append((char) c);
				}
				// If this is HTTP/1.0 or later send a MIME header
				String get = request.toString();
				int firstSpace = get.indexOf(' ');  //忽略掉POST类似的请求方法，直接进入寻找出文件名 index.html
				int secondSpace = get.indexOf(' ', firstSpace + 1);
				String theFile = get.substring(firstSpace + 1, secondSpace);
				if (get.indexOf("HTTP") != -1) {
					out.write("HTTP/1.0 302 FOUND\r\n");
					Date now = new Date();
					out.write("Date: " + now + "\r\n");
					out.write("Server: Redirector 1.0\r\n");
					out.write("Location: " + newSite + theFile + "\r\n");
					out.write("Content-type: text/html\r\n\r\n");
					out.flush();
				}
				// Not all browsers support redirection so we need to
				// produce HTML that says where the document has moved to.
				out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
				out.write("<BODY><H1>Document moved</H1>\r\n");
				out.write("The document " + theFile
						+ " has moved to\r\n<A HREF=\"" + newSite + theFile
						+ "\">" + newSite + theFile
						+ "</A>.\r\n Please update your bookmarks<P>");
				out.write("</BODY></HTML>\r\n");
				out.flush();

			} // end try
			catch (IOException ex) {
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (IOException ex) {
				}
			}

		} // end run

	}

	public static void main(String[] args) {

		int thePort;
		String theSite;

		try {
			theSite = args[0];
			// trim trailing slash
			if (theSite.endsWith("/")) {
				theSite = theSite.substring(0, theSite.length() - 1);
			}
		} catch (Exception ex) {
			System.out
					.println("Usage: java Redirector http://www.newsite.com/ port");
			return;
		}

		try {
			thePort = Integer.parseInt(args[1]);
		} catch (Exception ex) {
			thePort = 80;
		}

		Thread t = new Thread(new Redirector(theSite, thePort));
		t.start();

	} // end main

}
