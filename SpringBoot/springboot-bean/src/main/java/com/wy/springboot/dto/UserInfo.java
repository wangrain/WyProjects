package com.wy.springboot.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 名称：  UserInfo
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:  用于传输的UserInfo
 */
@Data
@ToString
public class UserInfo {
    private String userGuid;

    private String userName;

    private String userMobile;

    private String userEmail;

    private Date registerTime;
}
