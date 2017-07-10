package com.wy.service.impl;

import com.wy.dao.TestADao;
import com.wy.dao.TestBDao;
import com.wy.interfaces.BizService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 名称：  BizServiceImpl
 * 作者:   rain.wang
 * 日期:   2017/5/12
 * 简介:
 */
@Service
public class BizServiceImpl implements BizService {

    @Resource
    TestADao testADao;
    @Resource
    TestBDao testBDao;

    @Override
    public boolean doBusiness_NT() {
        testADao.insertA();
        testBDao.updateB();
        testADao.updateA();

        return false;
    }

    public static void main(String[] args) {
        BizService service = new BizService(){
            @Override
            public boolean doBusiness_NT() {
                System.out.println("success!");
                return true;
            }
        };
        service.doBusiness_NT();
    }
}
