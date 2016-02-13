package com.webserver.dhalf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DhalfTcpServer implements Runnable{

	
	private ServerSocket server;
	private volatile boolean done=false;
	public static void main(String[] args) {
		DhalfTcpServer d=new DhalfTcpServer();
		d.startServer(8080);

	}

	
	public void startServer(int port){
		try {
			this.server=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		while(!done){
			try {
				Socket data=server.accept();
				run(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	private void run(Socket data){
		new Thread(new DealDataThread(data)).start();
	}
	
	private static class DealDataThread implements Runnable{

		public DealDataThread(Socket data){
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
