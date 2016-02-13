package ch06;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import com.macfaq.io.SafeBufferedReader; 

public class Weblog {

  public static void main(String[] args) {

    Date start = new Date();
    try {
      FileInputStream fin =  new FileInputStream("F:/workspace/network/net3programe/src/ch06/access.txt");
      Reader in = new InputStreamReader(fin);
      SafeBufferedReader bin = new SafeBufferedReader(in);
      
      String entry = null;
      while ((entry = bin.readLine()) != null) {
        
        // separate out the IP address
        int index = entry.indexOf(' ', 0);
        String ip = entry.substring(0, index);
        String theRest = entry.substring(index, entry.length());
        
        // find the hostname and print it out
        try {
          InetAddress address = InetAddress.getByName(ip);
          System.out.println(address.getHostName() + theRest);
        }
        catch (UnknownHostException ex) {
          System.out.println(entry);
        }
        
      } // end while
    }
    catch (IOException ex) {
      System.out.println("Exception: " + ex);
    }
    
    Date end = new Date();
    long elapsedTime = (end.getTime()-start.getTime())/1000;
    System.out.println("Elapsed time: " + elapsedTime + " seconds");

  }  // end main

}

