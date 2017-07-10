package com.wy.springboot.enums;

/**
 * 名称：  ErrorEnum
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:
 */
public enum ErrorEnum {
    SUCCESS("success","成功"),
    ERROR("error","失败");

    public String errCode;
    public String errMsg;

    ErrorEnum(String errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
