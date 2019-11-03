package com.app.web.authorization.manager.impl;

import com.app.web.authorization.config.Constants;
import com.app.web.authorization.manager.TokenManage;
import com.app.web.authorization.model.TokenModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @see TokenManage
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component("redisTokenManage")
public class RedisTokenManage implements TokenManage {

    private RedisTemplate<Long, String> redis;

    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public TokenModel createToken(long userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public TokenModel getToken(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        return new TokenModel();
    }

    @Override
    public void storeToken(String token, int expires) {

    }

    public boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        return true;
    }

    public void deleteToken(String token) {

        // TODO 待完善删除 token 功能，类似登出
//        redis.delete(token);
    }
}