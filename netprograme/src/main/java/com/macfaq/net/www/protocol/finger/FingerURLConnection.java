package com.macfaq.net.www.protocol.finger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

//16-2
public class FingerURLConnection extends URLConnection {

	private Socket connection = null;
	public final static int DEFAULT_PORT = 79;

	public FingerURLConnection(URL u) {
		super(u);
	}

	@Override
	public synchronized InputStream getInputStream() throws IOException {

		if (!connected)
			this.connect();
		InputStream in = this.connection.getInputStream();
		return in;

	}

	@Override
	public String getContentType() {
		return "text/plain";
	}

	@Override
	public synchronized void connect() throws IOException {

		if (!connected) {
			int port = url.getPort();
			if (port < 1 || port > 65535) {
				port = DEFAULT_PORT;
			}
			this.connection = new Socket(url.getHost(), port);
			OutputStream out = this.connection.getOutputStream();
			String names = url.getFile();
			if (names != null && !names.equals("")) {
				// delete initial /
				names = names.substring(1);
				//names = URLDecoder.decode(names);
				names = URLDecoder.decode(names, "ASCII");
				byte[] result;
				try {
					result = names.getBytes("ASCII");
				} catch (UnsupportedEncodingException ex) {
					result = names.getBytes();
				}
				out.write(result);
			}
			out.write('\r');
			out.write('\n');
			out.flush();
			this.connected = true;
		}
	}
}
