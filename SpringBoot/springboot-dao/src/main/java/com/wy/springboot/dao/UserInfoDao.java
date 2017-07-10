package com.wy.springboot.dao;

import com.wy.springboot.bean.UserInfoQ;
import com.wy.springboot.persist.mapper.UmUserInfoMapper;
import com.wy.springboot.persist.po.UmUserInfo;
import com.wy.springboot.persist.po.UmUserInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 名称：  UserInfoDao
 * 作者:   rain.wang
 * 日期:   2017/5/13
 * 简介:
 */
@Slf4j
@Component
public class UserInfoDao {

    @Resource
    UmUserInfoMapper umUserInfoMapper;

    /**
     * 新增用户基础信息
     * @param userInfo
     * @return
     */
    public int addUserInfo(UmUserInfo userInfo){
        return umUserInfoMapper.insert(userInfo);
    }

    private UmUserInfoExample getUserInfoListExample(UserInfoQ userInfOQ){
        UmUserInfoExample example = new UmUserInfoExample();
        if(userInfOQ !=null){
            log.debug("查询条件如下：{}",userInfOQ.toString());
            UmUserInfoExample.Criteria criteria = example.createCriteria();
            if(!StringUtils.isEmpty(userInfOQ.getUserName())){
                criteria.andUserNameLike(userInfOQ.getUserName());
            }
            if(!StringUtils.isEmpty(userInfOQ.getUserEmail())){
                criteria.andUserEmailLike(userInfOQ.getUserEmail());
            }
            if(!StringUtils.isEmpty(userInfOQ.getUserMobile())){
                criteria.andUserMobileLike(userInfOQ.getUserMobile());
            }
            if(userInfOQ.getRegisterTimeBegin() != null){
                criteria.andRegisterTimeGreaterThanOrEqualTo(userInfOQ.getRegisterTimeBegin());
            }
            if(userInfOQ.getRegisterTimeEnd() != null){
                criteria.andRegisterTimeLessThanOrEqualTo(userInfOQ.getRegisterTimeEnd());
            }
        }
        return example;
    }

    /**
     * 翻页查询用户列表
     * @param userInfOQ
     * @param offset
     * @param limit
     * @return
     */
    public List<UmUserInfo> getUserInfoList(UserInfoQ userInfOQ, int offset, int limit){
        UmUserInfoExample example = getUserInfoListExample(userInfOQ);
        example.setOrderByClause("REGISTER_TIME desc");
        return umUserInfoMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,limit));
    }

    /**
     * 返回getUserInfoList 方法的总个数
     * @param userInfOQ
     * @return
     */
    public long getUserInfoListCount(UserInfoQ userInfOQ) {
        UmUserInfoExample example = getUserInfoListExample(userInfOQ);
        return umUserInfoMapper.countByExample(example);
    }
}


