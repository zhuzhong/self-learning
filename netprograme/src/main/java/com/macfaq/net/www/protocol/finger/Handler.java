package com.macfaq.net.www.protocol.finger;

import java.net.*;
import java.io.*;

public class Handler extends URLStreamHandler {

	public int getDefaultPort() {
		return 79;
	}

	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return new FingerURLConnection(u);
	}

	//因为将finger url协议定义为层次结构，所以不需要重写parseurl方法
}
