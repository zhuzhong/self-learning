package ch10;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {

	public final static int DEFAULT_PORT = 8080;

	public static void main(String[] args) {

		int port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
				if (port < 0 || port >= 65536) {
					System.out.println("Port must between 0 and 65535");
					return;
				}
			} catch (NumberFormatException ex) {
				// use default port
			}

		}
		ServerSocket server = null;
		try {

			server = new ServerSocket(port);
              System.out.println("serversocket init successful....");
			Socket connection = null;
			while (true) {

				try {
					connection = server.accept();
					Writer out = new OutputStreamWriter(
							connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString() + "\r\n");
					out.flush();
					//connection.close();
				} catch (IOException ex) {
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (IOException ex) {
					}
				}

			} // end while

		} // end try
		catch (IOException ex) {
			System.err.println(ex);
		} // end catch
		finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	} // end main

} // end DaytimeServer
