package ch15;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleCacheRequest extends java.net.CacheRequest {

	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Override
	public OutputStream getBody() throws IOException {
		return out;
	}

	@Override
	public void abort() {
		out = null;
	}

	public byte[] getData() {
		if (out == null)
			return null;
		else
			return out.toByteArray();
	}

}
