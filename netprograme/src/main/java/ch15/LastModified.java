package ch15;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

 public class LastModified {

  public static void main(String args[]) {

    //for (int i=0; i < args.length; i++) {
      try {
        URL u = new URL("http://cabin.ceair.com");
        HttpURLConnection http = (HttpURLConnection) u.openConnection();
        http.setRequestMethod("HEAD");
        System.out.println(u + "was last modified at " 
         + new Date(http.getLastModified()));
        System.out.println(http.getResponseMessage());
        System.out.println(http.getResponseCode());
      }  // end try
      catch (MalformedURLException ex) {
        System.err.println( " is not a URL I understand");
      }
      catch (IOException ex) {
        System.err.println(ex);
      }      
      System.out.println();       
    //}  // end for
      
  }  // end main

}  // end LastModified
