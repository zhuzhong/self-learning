/**
 * 
 */
package com.zz.learning.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 *
 */
public class ReentrantLockDemo {

    
    private static ReentrantLock lock=new ReentrantLock();
    private static Condition con=lock.newCondition();
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("main thread running...");
        new Thread1Demo().start();
        new Thread2Demo().start();
        System.out.println("main thread end...");
    }
    
    
    
 private static class Thread2Demo extends Thread{
        
        public void run(){
            try{
                
                
                lock.lock();
                System.out.println(String.format("thread id=%s,get lock...",Thread.currentThread().getId()));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                con.signalAll();
                System.out.println(String.format("thread id=%s,signalAll...",Thread.currentThread().getId()));
              
            }finally{
                lock.unlock();
            }
        }
    }
 
 
    
    private static class Thread1Demo extends Thread{
        
        public void run(){
            try{
                
                
                lock.lock();
                System.out.println(String.format("thread id=%s,get lock...",Thread.currentThread().getId()));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    System.out.println(String.format("thread id=%s,begin await...",Thread.currentThread().getId()));
                    con.await();
                    System.out.println(String.format("thread id=%s,await over...",Thread.currentThread().getId()));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }finally{
                lock.unlock();
            }
        }
    }

}
