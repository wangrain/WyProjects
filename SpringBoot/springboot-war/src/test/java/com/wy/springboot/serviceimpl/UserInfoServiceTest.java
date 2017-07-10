package com.wy.springboot.serviceimpl;

import com.wy.springboot.bean.Pager;
import com.wy.springboot.dto.UserInfo;
import com.wy.springboot.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.UUID;

/**
 * 名称：  UserInfoServiceTest
 * 作者:   rain.wang
 * 日期:   2017/5/14
 * 简介:
 */
public class UserInfoServiceTest {

    private ClassPathXmlApplicationContext ctx;

    private UserInfoService userInfoService;

    @Before
    public void setUp(){
        ctx = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
        userInfoService = ctx.getBean("userInfoServiceImpl",UserInfoService.class);
    }

    @Test
    public void getUserInfoListTest(){
        Pager pager = new Pager();
        pager = userInfoService.getUserInfoList(null,pager);
        pager.getResult().forEach(userInfo -> System.out.println(userInfo.toString()));
    }

    @Test
    public void addUserInfoTest(){
        long beginTime = new Date().getTime();
        int i=0;
        for(;i<100;i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserGuid(UUID.randomUUID().toString().replace("-",""));
            userInfo.setRegisterTime(new Date());
            userInfo.setUserEmail("wy"+i+"@qq.com");
            userInfo.setUserMobile("1314124308"+i);
            userInfo.setUserName("wy"+i);
            userInfoService.addUserInfo(userInfo);
        }
        long endTime = new Date().getTime();
        long time = (endTime - beginTime)/1000;
        System.out.println("共插入"+i+"条记录，耗费时间："+time);

        getUserInfoListTest();
    }
}
