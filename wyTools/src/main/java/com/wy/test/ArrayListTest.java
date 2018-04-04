package com.wy.test;

import java.util.ArrayList;
import java.util.HashMap;


public class ArrayListTest {

	public static void main(String[] args) {
		int max = Integer.MAX_VALUE;// 	2147483647  
		ArrayList<Long> list = new ArrayList();
		long l = 0;
		list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        list.add(5L);
        System.out.println(list.size());
        list.stream().forEach(e-> System.out.print(e));
        list.remove(0);
        System.out.println("\n"+list.size());
        list.stream().forEach(e-> System.out.print(e));
        new HashMap().containsKey("");

    }
}
