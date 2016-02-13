package ch09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) {

		String hostname = "localhost";

		if (args.length > 0) {
			hostname = args[0];
		}

		PrintWriter out = null;
		BufferedReader networkIn = null;
		try {
			Socket theSocket = new Socket(hostname, 7);
			networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(theSocket.getOutputStream());
			System.out.println("Connected to echo server");

			while (true) {
				String theLine = userIn.readLine();
				if (theLine.equals("."))
					break;
				out.println(theLine);
				out.flush();
				System.out.println(networkIn.readLine());
			}
			theSocket.close();
		} // end try
		catch (IOException ex) {
			System.err.println(ex);
		} finally {
			try {
				if (networkIn != null)
					networkIn.close();
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}

	} // end main

} // end EchoClient
