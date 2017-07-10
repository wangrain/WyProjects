package com.wy.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 名称：  ThreadPoolExectorTest
 * 作者:   rain.wang
 * 日期:   2017/7/10
 * 简介:
 */
public class ThreadPoolExectorTest {
    public static void main(String[] args) {
        int corePoolSize = 3;
        int maximumPoolSize = 20;
        long keepAliveTime = 20;
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,   //核心线程数，即最小线程数
                        maximumPoolSize,    //最大线程数
                        keepAliveTime,      //当前线程数超过核心线程数时，超过的线程允许空闲的时间
                        TimeUnit.SECONDS,   //keepAliveTime的单位
                        new LinkedBlockingQueue<>(Integer.MAX_VALUE));
    }
}
