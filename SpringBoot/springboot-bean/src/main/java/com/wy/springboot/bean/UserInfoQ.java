package com.wy.springboot.bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 名称：  UserInfoQ
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:  用于查询的UmUserInfo
 */
@Data
@ToString
public class UserInfoQ {
    private String userGuid;

    private String userName;

    private String userMobile;

    private String userEmail;

    private Date registerTimeBegin;

    private Date registerTimeEnd;
}
