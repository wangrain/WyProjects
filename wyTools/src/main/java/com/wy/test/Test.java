package com.wy.test;

/**
 * 名称：  Test
 * 作者:   rain.wang
 * 日期:   2017/5/25
 * 简介:
 */
public class Test {
    public static int X=100;
    public final static int Y=200;
    public Test(){
        System.out.println("Test构造函数执行");
    }
    static{
        System.out.println("static语句块执行");
    }
    public static void display(){
        System.out.println("静态方法被执行");
    }
    public void display_1(){
        System.out.println("实例方法被执行");
    }
}
