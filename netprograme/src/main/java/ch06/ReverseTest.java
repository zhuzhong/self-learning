package ch06;

import java.net.*;

public class ReverseTest {

  public static void main (String[] args) {
  
    try {
      InetAddress ia = InetAddress.getByName("www.ceair.com");
      System.out.println(ia.getHostName());
      System.out.println(ia.getHostAddress());
      
      
      byte[] bs = new byte[] { (byte) 127, (byte) 0, (byte)0, (byte)1 };
      ia = InetAddress.getByAddress(bs);
      System.out.println(ia.getHostName());
      System.out.println(ia.getHostAddress());
      
      
      
      ia = InetAddress.getByName("127.0.0.1");
      System.out.println(ia.getHostName());
      System.out.println(ia.getHostAddress());
    }
    catch (Exception ex) {
      System.err.println(ex);
    }
   
  }

}
