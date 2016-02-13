package ch09;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//一个命令行的whois 客户端
public class WhoisClient {

  public final static int DEFAULT_PORT = 43;
  public final static String DEFAULT_HOST = "whois.internic.net";

  public static void main(String[] args) {

    String serverName = System.getProperty("WHOIS_SERVER", DEFAULT_HOST);  
    
    InetAddress server = null;
    try {
       server = InetAddress.getByName(serverName);
    }catch (UnknownHostException ex) {
      System.err.println("Error: Could not locate whois server " + server);
      System.err.println("Usage: java -DWHOIS_SERVER=hostname WhoisClient name");         
      return;
    }       
    Socket theSocket=null;
    try {
       theSocket = new Socket(server, DEFAULT_PORT);
      Writer out = new OutputStreamWriter(theSocket.getOutputStream(), 
       "8859_1");
      for (int i = 0; i < args.length; i++) out.write(args[i] + " ");
      out.write("\r\n");
      out.flush();
    //  InputStream raw = theSocket.getInputStream();
      InputStream in  = new BufferedInputStream(theSocket.getInputStream());
      int c;
      while ((c = in.read()) != -1) System.out.write(c);
    }catch (IOException ex) {
      System.err.println(ex);
    }finally{
    	if(theSocket!=null){
    		try {
				theSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

  }

}
