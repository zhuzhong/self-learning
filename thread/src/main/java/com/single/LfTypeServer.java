package com.single;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LfTypeServer implements Runnable {

	public static void main(String[] args) {
		
		LfTypeServer l=new LfTypeServer();
		try {
			l.startServer(8080, 3);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	private ServerSocket server;
	
	private Thread[] serverThreads;
	
	private volatile boolean  done=false;
	
	public synchronized void startServer(int port,int nThreads) throws IOException{
		server=new ServerSocket(port);
		serverThreads=new Thread[nThreads];
		
		for(int i=0;i<nThreads;i++){
			serverThreads[i]=new Thread(this,"threadname-"+i);
			serverThreads[i].start();
		}
		
	}
	
	
	@Override
	public void run() {
		/**这个循环可以使本线程进入下一次的等待，即线程不退出
		 * 维持其自己的生命周期
		 */
		while(!done){
			try {
				Socket data=server.accept();
				run(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void run(Socket data){
		System.out.println(Thread.currentThread().getName());
		try {
			data.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
