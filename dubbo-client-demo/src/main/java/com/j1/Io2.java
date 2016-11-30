/**
 * 
 */
package com.j1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public class Io2 {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier latch = new CyclicBarrier(3, new Total());
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new Worker(latch));
        es.execute(new Worker(latch));
        es.execute(new Worker(latch));
        
        
        es.execute(new Worker(latch));
        es.execute(new Worker(latch));
        es.execute(new Worker(latch));
        
        System.out.println("main thread name" + Thread.currentThread().getName());
        es.shutdown();

    }

    private static class Total implements Runnable {

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        public void run() {
            System.out.println(System.currentTimeMillis() + " finish...");

        }

    }

    private static class Worker implements Runnable {

        private CyclicBarrier latch;

        public Worker(CyclicBarrier latch) {
            this.latch = latch;
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

            try {
                latch.await();
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
