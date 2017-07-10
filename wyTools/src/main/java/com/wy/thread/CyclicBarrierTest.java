package com.wy.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by turbit on 2015/7/9.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int totalThread = 6;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i =0;i<totalThread;i++){
            String threadName = "Thread"+i;
            int num = i;
            new Thread(()->{
                System.out.println(threadName+" is begin running!");
                try {
                    Thread.sleep((num*1000));
                    System.out.println(threadName+" is begin await!");
                    cyclicBarrier.await();
                    System.out.println(threadName+" waked.");
                    Thread.sleep((num*1000));
                    System.out.println(threadName+" is end!");
                    //System.out.println(cyclicBarrier.getNumberWaiting()+" thread is awaiting!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
