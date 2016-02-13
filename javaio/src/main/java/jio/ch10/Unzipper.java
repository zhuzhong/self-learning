package jio.ch10;

import java.util.*;
import java.util.zip.*;
import java.io.*;

//java的zipfile 类能够解压普通压缩软件的zip格式文件
public class Unzipper {

	public static void main(String[] args) throws IOException {
		// ZipFile zf = new ZipFile(args[0]);

		ZipFile zf = new ZipFile(
				"I:/workspace/network/net3programe/src/javaio/jio/ch10/DirectInflater.zip");

		Enumeration e = zf.entries();
		while (e.hasMoreElements()) {
			ZipEntry ze = (ZipEntry) e.nextElement();
			System.out.println("Unzipping " + ze.getName());
			// FileOutputStream fout = new FileOutputStream(ze.getName());
			FileOutputStream fout = new FileOutputStream(
					"I:/workspace/network/net3programe/src/javaio/jio/ch10/DirectInflater.tt");
			InputStream in = zf.getInputStream(ze);
			for (int c = in.read(); c != -1; c = in.read()) {
				fout.write(c);
			}
			in.close();
			fout.close();
		}
		System.out.println("ook");
	}
}
