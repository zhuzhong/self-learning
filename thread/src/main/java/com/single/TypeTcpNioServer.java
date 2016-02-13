package com.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TypeTcpNioServer implements Runnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private ServerSocketChannel channel;
	private boolean done = false;
	private Selector selector;

	private SocketChannel data;

	public void startServer(int port) {
		try {
			channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			ServerSocket server = channel.socket();
			server.bind(new InetSocketAddress(port));

			selector = Selector.open();
			channel.register(selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		startServer(8080);

		while (true) {
			try {
				selector.select();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key=it.next();
				if(key.isReadable()||key.isWritable()){
					handleClient(key);
				}else if(key.isAcceptable()){
					try {
						handleServer(key);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				it.remove();
			}

		}
	}

	private void handleServer(SelectionKey key) throws IOException {
		SocketChannel sc=	channel.accept();
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);
		registeredClient(sc);
	}

	private void registeredClient(SocketChannel sc) {
		
		
	}

	private static class ClientInfo{
		ByteBuffer inBuf=ByteBuffer.allocateDirect(512);
		ByteBuffer outBuf=ByteBuffer.allocateDirect(512);
		boolean outputPending=false;
		SocketChannel channel;
	}
	
	private static String testString="thisisateststring";
	private Map<SocketChannel,ClientInfo> allClients=new HashMap<SocketChannel,ClientInfo>();
	
	private void handleClient(SelectionKey key) {
		  SocketChannel sc=(SocketChannel) key.channel();
		  ClientInfo ci=allClients.get(sc);
		  if(ci==null){
			  //throw exception 
		  }
		  if(key.isWritable()){
			  send(sc,ci);
		  }
		  
		  if(key.isReadable()){
			  recv(sc,ci);
		  }
		
	}

	//接收
	private void recv(SocketChannel sc, ClientInfo ci) {
		
	}

	//发送
	private void send(SocketChannel sc, ClientInfo ci) {
		
	}

}
