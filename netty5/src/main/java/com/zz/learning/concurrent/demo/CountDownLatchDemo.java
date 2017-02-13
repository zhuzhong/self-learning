/**
 * 
 */
package com.zz.learning.concurrent.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public class CountDownLatchDemo {

    
    private static CountDownLatch latch=new CountDownLatch(10);
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("main thread begin...");
        for(int i=0;i<10;i++){
            new ThreadDemo().start();
        }

        try {
            latch.await();
            System.out.println("main thread await...");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("main thread end...");
        
    }

    
    private static class ThreadDemo extends Thread{
        
        public void run(){
            System.out.println(String.format("thread id=%s checking...", Thread.currentThread().getId()));
            try {
                TimeUnit.SECONDS.sleep(5);
               
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(String.format("thread id=%s check over...", Thread.currentThread().getId()));
            latch.countDown();
        }
    }
}
