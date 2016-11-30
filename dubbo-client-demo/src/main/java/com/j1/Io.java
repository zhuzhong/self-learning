/**
 * 
 */
package com.j1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public class Io {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(3);
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new Worker(latch));
        es.execute(new Worker(latch));
        es.execute(new Worker(latch));
        latch.await();
        System.out.println("main thread name" + Thread.currentThread().getName());
        es.shutdown();
        
        CyclicBarrier barrier=new CyclicBarrier(3);
        
    }

    private static class Worker implements Runnable {

        private CountDownLatch latch;
        public Worker(CountDownLatch latch){
            this.latch=latch;
        }
        public void run() {

            try {
                System.out.println("sleep thread name" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("wake thread name" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            latch.countDown();
            
            

        }

    }
}
