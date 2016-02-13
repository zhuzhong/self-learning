package ch15;

import java.net.*;
import java.io.*;
//15-1
public class SourceViewer2 {

  public static void main (String[] args) {

   // if  (args.length > 0) {
      try {
        //Open the URLConnection for reading
        URL u = new URL("http://172.20.35.37:7080/esb/QueryPassengerInfoByFDDA?wsdl");
        URLConnection uc = u.openConnection();
        System.out.println(uc.getContentType());
        
        InputStream raw = uc.getInputStream();
        InputStream buffer = new BufferedInputStream(raw);       
        // chain the InputStream to a Reader
        Reader r = new InputStreamReader(buffer);
        
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

    //} //  end if

  } // end main

}  // end SourceViewer2
