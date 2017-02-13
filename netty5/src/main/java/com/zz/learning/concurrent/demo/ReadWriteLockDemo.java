/**
 * 
 */
package com.zz.learning.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Administrator
 *
 */
public class ReadWriteLockDemo {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static Lock readLock = lock.readLock();

    private static Lock writeLock = lock.writeLock();

    /**
     * @param args
     */
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
          
            //new ReadThreadDemo("read-"+i).start();
            new WriteThreadDemo("write-"+i).start();
        }
        
        for(int i=0;i<10;i++){
            
            new ReadThreadDemo("read-"+i).start();
            //new WriteThreadDemo("write-"+i).start();
        }
        
    }

    private static class ReadThreadDemo extends Thread {
        
        public ReadThreadDemo(String name){
          super(name);
        }
        public void run() {
            readLock.lock();
            System.out.println(String.format("thread id = %s,reading ", Thread.currentThread().getName()));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            readLock.unlock();
            System.out.println(String.format("thread id = %s,read over ", Thread.currentThread().getName()));
        }
    }

    private static class WriteThreadDemo extends Thread {
        public WriteThreadDemo(String name){
            super(name);
          }
        public void run() {
            writeLock.lock();
            System.out.println(String.format("thread id = %s,writing ", Thread.currentThread().getName()));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            writeLock.unlock();
            System.out.println(String.format("thread id = %s,write over ", Thread.currentThread().getName()));
        }
    }
    
}
