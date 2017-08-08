package com.wy.thread;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.*;

/**
 * 名称：  ThreadPoolExectorTest
 * 作者:   rain.wang
 * 日期:   2017/7/10
 * 简介:
 */
public class ThreadPoolExectorTest {
    public static void main(String[] args) {
        int corePoolSize = 3;
        int maximumPoolSize = 5;
        long keepAliveTime = 2;
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,   //核心线程数，即最小线程数
                        maximumPoolSize,    //最大线程数
                        keepAliveTime,      //当前线程数超过核心线程数时，超过的线程允许空闲的时间
                        TimeUnit.SECONDS,   //keepAliveTime的单位
                        new LinkedTransferQueue<>()
                );
        /*
         * SynchronousQueue 队列，新的请求直接开辟至最大线程数，
         *      线程池达到最大线程数后，继续请求将抛出异常；
         * LinkedBlockingQueue队列，线程池仅创建核心线程数，超过的请求将阻塞；
         * ArrayBlockingQueue队列，同LinkedBlockingQueue队列，
         *      线程池仅创建核心线程数，超过的线程将阻塞到队列中，
         *      超过队列长度的请求将获取新线程直至达到最大线程数，
         *      当请求灌满队列，且达到最大线程数，再次请求时将抛出异常。
         * PriorityBlockingQueue 队列，核心线程池满后，按照优先级执行队列中线程。
         */
        System.out.println("初始线程池中有"+threadPoolExecutor.getActiveCount()+"个线程。");
        try {
            for (int i=0;i<15;){
                String threadName = "thread "+(i++);
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(threadName+" is beginning!");
                        System.out.println("当前线程池中有"+threadPoolExecutor.getActiveCount()+"个线程。");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            System.out.println(threadName+" throw an Exception:");
                            e.printStackTrace();
                        }
                        System.out.println(threadName+" is ending!");
                    }
                });
            }
            System.out.println("Main Thread begin sleep !");
            Thread.sleep(10000);
            System.out.println("Main Thread is awake !");
        } catch (Exception e) {
            System.out.println("Main Thread throw an Exception:");
            e.printStackTrace();
        }
        System.out.println("当前线程池中有"+threadPoolExecutor.getActiveCount()+"个线程。");
        threadPoolExecutor.shutdown();
        System.out.println("主线程池关闭。");
    }
}
