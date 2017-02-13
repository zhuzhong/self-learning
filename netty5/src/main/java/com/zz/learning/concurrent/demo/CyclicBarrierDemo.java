/**
 * 
 */
package com.zz.learning.concurrent.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public class CyclicBarrierDemo {

    
    private static CyclicBarrier barrier=new CyclicBarrier(10, new Runnable() {
        
        @Override
        public void run() {
            //由最后一个完成的线程来执行聚合任务
          System.out.println(String.format("i am %s,完成聚合动作...",Thread.currentThread().getId()));
            
        }
    });
    /**
     * @param args
     */
    public static void main(String[] args) {
       System.out.println("main thread running...");
       for(int i=0;i<10;i++){
           new ThreadDemo().start();
       }
       System.out.println("main thread end...");
    }

    
    private static class ThreadDemo extends Thread{
        public void run(){
            System.out.println(String.format("thread id is %s,running...", Thread.currentThread().getId()));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println(String.format("thread id is %s,over...", Thread.currentThread().getId()));
            try {
                barrier.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
