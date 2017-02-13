/**
 * 
 */
package com.zz.learning.concurrent.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public class SemaphoreDemo {

    
    private static Semaphore phore=new Semaphore(5);
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("main thread running...");
        for(int i=0;i<20;i++){
            new ThreadDemo().start();
        }
        System.out.println("main thread end...");
    }

    
    private static class ThreadDemo extends Thread{
        
        public void run(){
            System.out.println(String.format("thread id =%s,running...",Thread.currentThread().getId()));
            try {
                phore.acquire();
                System.out.println(String.format("thread is = %s, acquire success", Thread.currentThread().getId()));
                TimeUnit.SECONDS.sleep(1);
                phore.release();
                System.out.println(String.format("thread is = %s, release success", Thread.currentThread().getId()));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        }
    }
}
