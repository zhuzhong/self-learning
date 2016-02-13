package ch13;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;

public class UDPEchoServer extends UDPServer {

  public final static int DEFAULT_PORT = 7;

  public UDPEchoServer() throws SocketException {
    super(DEFAULT_PORT); 
  }

  public void respond(DatagramPacket packet) {

    try {
      DatagramPacket outgoing = new DatagramPacket(packet.getData(), 
       packet.getLength(), packet.getAddress(), packet.getPort());
      ds.send(outgoing);
    }catch (IOException ex) {
      System.err.println(ex);
    }
    
  }

  public static void main(String[] args) {
 
   try {
     UDPServer server = new UDPEchoServer();
     server.start();
   }
   catch (SocketException ex) {
     System.err.println(ex);
   }
 
  }

}
