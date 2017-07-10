package com.wy.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 名称：  LogTest
 * 作者:   rain.wang
 * 日期:   2017/5/5
 * 简介:
 */
@Slf4j
@Component
public class LogTest {
    @PostConstruct
    private void init (){
        log.debug("log level debug test ...");
        log.info("log level info test ...");
        log.warn("log level warn test ...");
        log.error("log level error test ...");
    }
}
