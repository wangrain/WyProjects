package com.wy.springboot.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 名称：  Pager
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:
 */
@Data
@ToString
public class Pager implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Integer MAX_PAGE_SIZE = Integer.valueOf(99999);
    public static final Integer MAX_PAGE = Integer.valueOf(5);
    private int begin = 0;
    private int end = 10;
    private int pageNo = 1;
    private int pageSize = 10;
    private long totalCount;
    private List<?> result;
    private List<Integer> pages = new ArrayList();
    private int pageCount = 0;
    private String code;
    private String msg;

    public Pager(){
    }
    public Pager(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
