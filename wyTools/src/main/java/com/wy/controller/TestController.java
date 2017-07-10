package com.wy.controller;

import com.alibaba.fastjson.JSON;
import com.wy.bean.Log;
import com.wy.interfaces.BizService;
import com.wy.interfaces.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 名称：  TestController
 * 作者:   rain.wang
 * 日期:   2017/5/12
 * 简介:
 */
@Controller
public class TestController {

    @Resource
    BizService bizService;
    @Resource
    LogService logService;


    @RequestMapping("/test/doBusiness")
    @ResponseBody
    public String listCpFlowByPage(HttpServletRequest request){
        Log log = new Log();
        try{
            bizService.doBusiness_NT();
            log.setResult("success");
        }catch (Exception e){
            log.setResult("fail");
        }finally {
            logService.insertLog(log);
        }
        return JSON.toJSONString(log);

    }
}
