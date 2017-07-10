package com.wy.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 名称：  Person
 * 作者:   rain.wang
 * 日期:   2017/4/7
 * 简介:
 */
@Data
public class Person {
    String name;
    Date birth;
    List<Person> parents;
}
