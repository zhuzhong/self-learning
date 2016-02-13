package com.macfaq.net.www.protocol.time;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

	protected URLConnection openConnection(URL u) throws IOException {
		return new TimeURLConnection(u);
	}
}
