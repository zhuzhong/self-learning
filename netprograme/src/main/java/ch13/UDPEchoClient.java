package ch13;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {

  public final static int DEFAULT_PORT = 7;

  public static void main(String[] args) {

    String hostname = "localhost";
    int port = DEFAULT_PORT;

    if (args.length > 0) {
      hostname = args[0];
    }

    try {
      InetAddress ia = InetAddress.getByName(hostname);
      SenderThread sender = new SenderThread(ia, port);
      sender.start();
      
      Thread receiver = new ReceiverThread(sender.getSocket());
      receiver.start();
    }
    catch (UnknownHostException ex) {
      System.err.println(ex);
    }
    catch (SocketException ex) {
      System.err.println(ex);
    }

  }  // end main

}
