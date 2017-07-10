package com.wy.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wy.springboot.bean.Pager;
import com.wy.springboot.bean.UserInfoQ;
import com.wy.springboot.dto.UserInfo;
import com.wy.springboot.enums.ErrorEnum;
import com.wy.springboot.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 名称：  UserInfoController
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:
 */
@Slf4j
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @PostMapping(value = "/add")
    public String addUserInfo(UserInfo userInfo){
        try {
            int num = userInfoService.addUserInfo(userInfo);
            if(num == 1){
                log.info("新增用户成功：{}",userInfo.toString());
            }else{
                log.warn("新增用户失败：{}",userInfo.toString());
                return ErrorEnum.ERROR.errCode;
            }
        }catch (Exception e){
            log.error("新增用户异常：{}",userInfo.toString(),e);
        }
        return ErrorEnum.SUCCESS.errCode;
    }

    @GetMapping(value = "/getList")
    public String getUserInfoList(UserInfoQ userInfoQ,Pager pager){
        try {
            pager = userInfoService.getUserInfoList(userInfoQ, pager);
        }catch (Exception e){
            pager.setTotalCount(0);
            pager.setMsg("查询异常");
            log.error("查询用户信息列表异常：",e);
        }
        return JSON.toJSONStringWithDateFormat(pager,"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }
}
