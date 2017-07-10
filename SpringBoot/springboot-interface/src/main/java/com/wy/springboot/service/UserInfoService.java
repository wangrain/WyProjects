package com.wy.springboot.service;

import com.wy.springboot.bean.Pager;
import com.wy.springboot.bean.UserInfoQ;
import com.wy.springboot.dto.UserInfo;

/**
 * 名称：  UserInfoService
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:  用户信息业务接口
 */
public interface UserInfoService {
    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int addUserInfo(UserInfo userInfo);

    /**
     * 分页查询用户信息
     * @param userInfOQ
     * @param pager
     * @return
     */
    Pager getUserInfoList(UserInfoQ userInfOQ, Pager pager);
}
