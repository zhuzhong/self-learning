package com.zz.learning.tailf;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Tail implements LogFileTailerListener {
	public void newLogFileLine(String line) {
		try {
			System.out.println(new String(line.getBytes("iso8859-1"), "utf-8")); //防乱码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Tail t = new Tail();
		t.runTailer("D:/用户目录/Downloads/catalina.out");
	}

	public void runTailer(String filename) {
		// String filename = "";
		LogFileTailer tailer = new LogFileTailer(new File(filename));
		// tailer.setTailing(true);
		tailer.addLogFileTailerListener(this);
		tailer.start();
	}
}