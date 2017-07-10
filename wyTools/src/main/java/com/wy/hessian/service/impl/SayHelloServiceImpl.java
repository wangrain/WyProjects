package com.wy.hessian.service.impl;

import com.wy.hessian.service.SayHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 名称：  SayHelloServiceImpl
 * 作者:   rain.wang
 * 日期:   2017/5/5
 * 简介:
 */
@Slf4j
@Service
public class SayHelloServiceImpl implements SayHelloService{

    @PostConstruct
    private void init (){
        log.debug("log level debug test ...");
        log.info("log level info test ...");
        log.warn("log level warn test ...");
        log.error("log level error test ...");
    }

    @Override
    public String sayHello() {
        String s = "Hello World!";
        log.info(s);
        return s;
    }
}
