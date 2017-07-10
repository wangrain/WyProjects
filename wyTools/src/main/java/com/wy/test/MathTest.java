package com.wy.test;

/**
 * 名称：  MathTest
 * 作者:   rain.wang
 * 日期:   2017/4/12
 * 简介:
 */
public class MathTest {
    public static void main(String[] args) {

        double  rate = (Math.round((double)53/100000*100));
        System.out.println(rate);

        int sendCount = 0;
        int timeStep = 120;
        for(;sendCount<6;sendCount++) {
            System.out.println("sendCount="+sendCount+",pow="+Math.pow(2, sendCount));
//            System.out.println((Math.pow(2, sendCount) * timeStep));
        }
    }
}
