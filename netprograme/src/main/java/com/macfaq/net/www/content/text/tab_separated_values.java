package com.macfaq.net.www.content.text;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ContentHandler;
import java.net.URLConnection;
import java.util.Vector;

import com.macfaq.io.SafeBufferedReader; // From Chapter 4

public class tab_separated_values extends ContentHandler {

	public Object getContent(URLConnection uc) throws IOException {

		String theLine;
		Vector lines = new Vector();

		InputStreamReader isr = new InputStreamReader(uc.getInputStream());
		SafeBufferedReader in = new SafeBufferedReader(isr);
		while ((theLine = in.readLine()) != null) {
			String[] linearray = lineToArray(theLine);
			lines.addElement(linearray);
		}

		return lines;

	}

	
	private String[] lineToArray2(String line){
		return line.split("\t");
	}
	
	
	private String[] lineToArray(String line) {

		int numFields = 1;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '\t')
				numFields++;
		}
		String[] fields = new String[numFields];
		int position = 0;
		for (int i = 0; i < numFields; i++) {
			StringBuffer buffer = new StringBuffer();
			while (position < line.length() && line.charAt(position) != '\t') {
				buffer.append(line.charAt(position));  //一个字符一个字符的读取
				position++;
			}
			fields[i] = buffer.toString();
			position++;
		}

		return fields;

	}
	
	/*
	 * linetoArray与lineArray2的区别在于如何对待tab空格，
	 */
	
	public static void main(String args[]){
		tab_separated_values t=new tab_separated_values();
		String line="a	b	c	d";
		String [] linstr=t.lineToArray(line);
		
		linstr=t.lineToArray2(line);
	}
}
