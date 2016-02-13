package com.webserver.lf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LfTcpServer implements Runnable{

	
	private ServerSocket server=null;
	private ExecutorService threadPool=null;
	private volatile boolean done=false;
	
	public synchronized void startServer(int port,int nThreads) throws IOException{
		server=new ServerSocket(port);
		threadPool=Executors.newFixedThreadPool(nThreads);
		for(int i=0;i<nThreads;i++)
		   threadPool.execute(this);
	}
	public static void main(String[] args) {
		LfTcpServer l=new LfTcpServer();
		try {
			l.startServer(8080, 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
//		while(!done){
//			
//		}
		
		try {
			Socket data=server.accept();
			run(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void run(Socket data){
	         System.out.println("oook");
	         try {
				data.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
