package com.app.web.controller.manager.impl;

import com.app.web.controller.manager.TokenManage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * 通过Redis存储和验证token的实现类
 * @see TokenManage
 * @author ScienJus
 * @date 2015/7/31.
 */
public class RedisTokenManage implements TokenManage {

    private RedisTemplate<Long, String> redis;

    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public void add(String token, String val) {
        //存储到redis并设置过期时间
//        return model;
    }

    public String get(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        return null;
    }

    public boolean exists(String token) {
        if (token == null) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        return true;
    }

    public void del(String token) {

        // TODO 待完善删除 token 功能，类似登出
//        redis.delete(token);
    }
}