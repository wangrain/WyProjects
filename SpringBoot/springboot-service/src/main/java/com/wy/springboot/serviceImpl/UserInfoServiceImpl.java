package com.wy.springboot.serviceImpl;

import com.wy.springboot.bean.Pager;
import com.wy.springboot.bean.UserInfoQ;
import com.wy.springboot.dao.UserInfoDao;
import com.wy.springboot.dto.UserInfo;
import com.wy.springboot.persist.po.UmUserInfo;
import com.wy.springboot.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 名称：  UserInfoServiceImpl
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:  用户信息业务实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoDao userInfoDao;

    /**
     * 新增用户
     *
     * @param userInfo
     * @return
     */
    public int addUserInfo(UserInfo userInfo) {
        if(userInfo == null){
            return 0;
        }
        UmUserInfo umUserInfo = new UmUserInfo();
        BeanUtils.copyProperties(userInfo,umUserInfo);
        return userInfoDao.addUserInfo(umUserInfo);
    }

    /**
     * 分页查询用户信息
     *
     * @param userInfOQ
     * @param pager
     * @return
     */
    public Pager getUserInfoList(UserInfoQ userInfOQ, Pager pager) {
        List<UmUserInfo> list = userInfoDao.getUserInfoList(userInfOQ,pager.getBegin(),pager.getPageSize());
        long count = userInfoDao.getUserInfoListCount(userInfOQ);
        List<UserInfo> resultList = new ArrayList<>();
        list.forEach(umUserInfo -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(umUserInfo,userInfo);
            resultList.add(userInfo);
        });
        pager.setResult(resultList);
        pager.setTotalCount(count);
        return pager;
    }
}
