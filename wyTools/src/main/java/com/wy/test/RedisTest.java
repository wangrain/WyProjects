package com.wy.test;

import redis.clients.jedis.Jedis;

/**
 * 名称：  com.wy.test.RedisTest
 * 作者:   rain.wang
 * 日期:   2016/6/30
 * 简介:
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.132",7000);
        System.out.println("conn to server success!");
        System.out.println("server is running :"+jedis.ping());
        /**
        for(int i=0;i<1000000;i++) {
            jedis.set("radom:"+i,String.valueOf(Math.random()));
        }
         */

        System.out.println("=====开始获取随机数。");
        int j=1;
        for(int i=0;i<1000000;i++) {
            String value = jedis.get("radom:"+i);
            /**
            if(i%10000==0){
                System.out.println("第"+(j++)+"万个随机数："+value);
            }
             */
        }
        System.out.println("=====获取随机数完毕。");
    }
}
