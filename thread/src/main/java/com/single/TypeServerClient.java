package com.single;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TypeServerClient  implements Runnable{

	
	private Socket socket;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public void start(){
		try {
			socket=new Socket("localhost",8080);
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		
		
	}

}
