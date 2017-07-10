package com.wy.bean;

import com.wy.util.SerializeTranscoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.List;

/**
 * 名称：  RedisQueue
 * 作者:   rain.wang
 * 日期:   2017/5/12
 * 简介:
 */
@Data
@Slf4j
@Scope("prototype")
public class RedisQueue<T extends Serializable> {

    private String qName = "default_q";

    private int blockTime = 10;

    @Autowired(required=false)
    private JedisCluster jedisCluster;

    /**
     * 向redis队列中push数据
     */
    public void push(T t){
        if(jedisCluster == null){
            throw new RuntimeException("系统队列配置错误，请检查");
        }
        long beginInMillions = System.currentTimeMillis();
        try {
            log.debug("push队列key："+qName+"，value："+t);
            byte[] valueByte = new SerializeTranscoder<Serializable>().serialize(t);
            byte[] base64Byte = Base64.encodeBase64(valueByte);
            String objectStr = new String(base64Byte);
            jedisCluster.lpush(qName,objectStr);
        } catch (Exception e) {
            log.error(qName+"push队列失败",e);
        }
        long endInMillions = System.currentTimeMillis();
        log.debug(qName+"push队列用时(毫秒)："+(endInMillions-beginInMillions));
    }

    /**
     * 从redis队列中pop数据
     * @return
     */
    @SuppressWarnings("unchecked")
    public T pop(){
        if(jedisCluster == null){
            throw new RuntimeException("系统队列配置错误，请检查");
        }
        if(blockTime <= 0){
            log.warn("配置name"+qName+"的超时时间为"+blockTime);
            throw new RuntimeException("系统队列配置错误，请检查");
        }

        String result = null;
        Serializable resultObject = null;
        long beginInMillions = System.currentTimeMillis();
        try {
            List<String> resultList = jedisCluster.brpop(blockTime, qName);
            if(resultList!=null && resultList.size()==2){
                result = resultList.get(1);
                if(result!=null){
                    byte[] base64Byte = result.getBytes();
                    byte[] oriByte = Base64.decodeBase64(base64Byte);
                    resultObject = new SerializeTranscoder<Serializable>().deserialize(oriByte);
                }
            }
        } catch (Exception e) {
            log.error(qName+"阻塞pop队列出现异常",e);
        }
        long endInMillions = System.currentTimeMillis();
        log.debug(qName+"阻塞pop队列用时(毫秒)："+(endInMillions-beginInMillions));
        return (T)resultObject;
    }
}