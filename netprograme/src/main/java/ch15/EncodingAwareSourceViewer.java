package ch15;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {

  public static void main (String[] args) {

  //  for (int i = 0; i < args.length; i++) {  
       
      try {
        // set default encoding
        String encoding = "ISO-8859-1";
        URL u = new URL("http://cabin1.ceair.com");
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        System.out.println("contentLength="+uc.getContentLength());
        int encodingStart = contentType.indexOf("charset=");
        if (encodingStart != -1) {
            encoding = contentType.substring(encodingStart+8);
            
        }
        System.out.println("encoding="+encoding);
        InputStream in = new BufferedInputStream(uc.getInputStream());   
        Reader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
          System.out.print((char) c);
        } 
      }
      catch (MalformedURLException ex) {
        System.err.println(args[0] + " is not a parseable URL");
      }
      catch (IOException ex) {
        System.err.println(ex);
      }

   // } //  end if

  } // end main

}  // end EncodingAwareSourceViewer
