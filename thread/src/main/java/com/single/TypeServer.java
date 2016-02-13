package com.single;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TypeServer implements Runnable,Cloneable {

	private Thread runner;
	private ServerSocket server;
    private Socket data;
    
	public void startServer(int port) {
		if (runner == null) {
			try {
				server = new ServerSocket(port);
				runner=new Thread(this);
				runner.start();
				System.out.println("server启动on port="+port);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public boolean getDone(){return false;}
	@Override
	public void run() {
		if(server!=null){
			while(!getDone()){
				try {
					Socket datasocket=server.accept();
					TypeServer newSocket=(TypeServer)super.clone();
					
					newSocket.server=null;
					newSocket.data=datasocket;
					newSocket.runner=new Thread(newSocket);
					newSocket.runner.start();
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				
			}
		}else{
			run(data);
		}

	}

	
	private static final byte welclome=0;
	private static final byte request=1;
	private static final byte response=2;
	
	
	private void test(){
		//OutputStreamWriter w=new Outp;
		
		
	}
	public void run(Socket data){
		try {
			DataOutputStream dos=new DataOutputStream(data.getOutputStream());
			dos.writeByte(welclome);
			
			DataInputStream dis=new DataInputStream(data.getInputStream());
			/**循环的目的，是为了解决读阻塞的
			 * 
			 */
			while(true){
				byte b=dis.readByte();
				if(b!=request){
					System.out.println("不是请求....");
					continue;
				}
				dos.writeByte(response);
				dos.writeUTF("thisisateststring");
				dos.flush();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		TypeServer t = new TypeServer();
		t.startServer(8080);

	}

}
