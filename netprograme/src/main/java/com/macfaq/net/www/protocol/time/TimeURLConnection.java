package com.macfaq.net.www.protocol.time;

import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import com.macfaq.net.www.content.application.x_time;

public class TimeURLConnection extends URLConnection {

	private Socket connection = null;
	public final static int DEFAULT_PORT = 37;

	public TimeURLConnection(URL u) {
		super(u);
	}

	@Override
	public String getContentType() {
		return "application/x-time";
	}

	@Override
	public Object getContent() throws IOException {
		ContentHandler ch = new x_time();
		return ch.getContent(this);
	}

	@Override
	public Object getContent(Class[] classes) throws IOException {
		ContentHandler ch = new x_time();
		return ch.getContent(this, classes);
	}

	public InputStream getInputStream() throws IOException {
		if (!connected)
			this.connect();
		return this.connection.getInputStream();
	}

	@Override
	public synchronized void connect() throws IOException {

		if (!connected) {
			int port = url.getPort();
			if (port < 0) {
				port = DEFAULT_PORT;
			}
			this.connection = new Socket(url.getHost(), port);
			this.connected = true;
		}
	}
}
