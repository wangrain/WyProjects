package com.wy.thread;

/**
 * Created by turbit on 2017/7/9.
 */
public class ExceptionTest {
    public static void main(String[] args) {

        int totalthread=5;
        try {
            for (int i=0;i<totalthread;i++){
                String threadName = "Thread"+i;
                new Thread(()->{
                    System.out.println(threadName+" begin running!");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if("Thread3".equals(threadName)){
                        System.out.println(1/0);
                    }
                    System.out.println(threadName+" is ended!");
                }).start();
            }
        } catch (Exception e) {
            //主线程无法catch子线程的异常
            System.out.println("ERROR:");
            e.printStackTrace();
        }
    }
}
