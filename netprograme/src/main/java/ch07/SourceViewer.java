package ch07;

import java.net.*;
import java.io.*;
//7-5
public class SourceViewer {

  public static void main (String[] args) {

   // if  (args.length > 0) {
      try {
        //Open the URL for reading
        URL u = new URL("http://ehr.ceair.com");
         
        InputStream in = u.openStream();
        // buffer the input to increase performance 
        in = new BufferedInputStream(in);       
        // chain the InputStream to a Reader
        Reader r = new InputStreamReader(in,"UTF-8");
        int c;
        while ((c = r.read()) != -1) {
          System.out.print((char) c);
        	//System.out.println(c);
        } 
      }
      catch (MalformedURLException ex) {
        System.err.println(" is not a parseable URL");
      }
      catch (IOException ex) {
        System.err.println(ex);
      }

    //} //  end if

  } // end main

}  // end SourceViewer
