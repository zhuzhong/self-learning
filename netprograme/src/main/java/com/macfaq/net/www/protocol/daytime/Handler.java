package com.macfaq.net.www.protocol.daytime;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

	public int getDefaultPort() {
		return 13;
	}

	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return new DaytimeURLConnection(u);
	}
}
