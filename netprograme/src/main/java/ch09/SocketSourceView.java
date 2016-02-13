package ch09;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketSourceView {
	public static void main(String[] args) {

	    String hostname;

	    if (args.length > 0) {
	      hostname = args[0];
	    }
	    else {
	      hostname = "cabin-sso1.ceair.com";
	    }

	    try {
	      Socket theSocket = new Socket(hostname, 8080);
	      InputStream timeStream = theSocket.getInputStream();
	      timeStream=new BufferedInputStream(timeStream);
	      Reader r = new InputStreamReader(timeStream);
	      StringBuffer time = new StringBuffer();
	      int c;
	      while ((c = r.read()) != -1) {
	    	 // time.append((char) c);
	    	  System.out.println((char) c);
	    	  
	      }
	      String timeString = time.toString().trim();  
	      System.out.println("It is " + timeString + " at " + hostname);
	      theSocket.close();
	    }  // end try
	    catch (UnknownHostException ex) {
	      System.err.println(ex);
	    }
	    catch (IOException ex) {
	      System.err.println(ex);
	    }

	  }  // end main

}
