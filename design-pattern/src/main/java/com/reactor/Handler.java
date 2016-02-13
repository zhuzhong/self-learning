package com.reactor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    private final static int DEFAULT_SIZE = 8092;

    private final SocketChannel socketChannel;

    private final SelectionKey seletionKey;

    private static final int READING = 0;

    private static final int SENDING = 1;
    
    private int state = READING;

    ByteBuffer inputBuffer = ByteBuffer.allocate(DEFAULT_SIZE);

    ByteBuffer outputBuffer = ByteBuffer.allocate(DEFAULT_SIZE);

    public Handler(Selector selector, SocketChannel channel) throws IOException {
        this.socketChannel = channel;
        socketChannel.configureBlocking(false);
        this.seletionKey = socketChannel.register(selector, 0);
        seletionKey.attach(this);
        seletionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        if(state == READING){
            read();
        }else if(state == SENDING){
            write();
        }

    }
    
    class Sender implements Runnable {

        @Override
        public void run() {
            try {
                socketChannel.write(outputBuffer);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(outIsComplete()){
                seletionKey.cancel();
            }
            
            
        }
        
    }

    private void write() {
        try {
            socketChannel.write(outputBuffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(outIsComplete()){
            seletionKey.cancel();
        }
        
    }

    private void read() {
        try {
            socketChannel.read(inputBuffer);
            if(inputIsComplete()){
                process();
                seletionKey.attach(new Sender());
                seletionKey.interestOps(SelectionKey.OP_WRITE);
                seletionKey.selector().wakeup();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
    public boolean inputIsComplete(){
        return false;
    }
    
    public boolean outIsComplete(){
        return false;
        
    }
    
    public void process(){
        
    }

}
