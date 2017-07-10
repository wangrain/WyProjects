package com.wy.test;

import java.util.Random;

/**
 * 名称：  RadomTest
 * 作者:   rain.wang
 * 日期:   2017/5/27
 * 简介:
 */
public class RadomTest {
    public static void main(String[] args) {

        Random random = new Random();
        for(int i=0;i<10;i++) {
            System.out.println(random.nextInt(15));
        }
    }
}
