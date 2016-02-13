package ch17;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Vector;

import com.macfaq.net.www.content.TabFactory;

public class TabLoader {

  public static void main (String[] args) {  
    
    URLConnection.setContentHandlerFactory(new TabFactory());
    
    for (int i = 0; i < args.length; i++) {
      try {
        URL u = new URL(args[i]);
        Object content = u.getContent();
        Vector v = (Vector) content;
        for (Enumeration e = v.elements() ; e.hasMoreElements() ;) {
          String[] sa = (String[]) e.nextElement();
          for (int j = 0; j < sa.length; j++) {
            System.out.print(sa[j] + "\t");
          }
          System.out.println();
        } 
      }
      catch (MalformedURLException ex) {
        System.err.println(args[i] + " is not a good URL"); 
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
