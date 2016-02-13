package com.macfaq.net.www.protocol.daytime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
// daytime://hostname:port
public class DaytimeURLConnection extends URLConnection {

	private Socket connection = null;
	public final static int DEFAULT_PORT = 13;

	public DaytimeURLConnection(URL u) {
		super(u);
	}

	@Override
	public synchronized InputStream getInputStream() throws IOException {

		if (!connected)
			connect();

		String header = "<html><head><title>The Time at " + url.getHost()
				+ "</title></head><body><h1>";
		String footer = "</h1></body></html>";
		InputStream in1 = new ByteArrayInputStream(header.getBytes("8859_1"));
		InputStream in2 = this.connection.getInputStream();
		InputStream in3 = new ByteArrayInputStream(footer.getBytes("8859_1"));

		SequenceInputStream result = new SequenceInputStream(in1, in2);
		result = new SequenceInputStream(result, in3);
		return result;

	}

	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public synchronized void connect() throws IOException {

		if (!connected) {
			int port = url.getPort();
			if (port <= 0 || port > 65535) {
				port = DEFAULT_PORT;
			}
			this.connection = new Socket(url.getHost(), port);
			this.connected = true;
		}
	}
}
