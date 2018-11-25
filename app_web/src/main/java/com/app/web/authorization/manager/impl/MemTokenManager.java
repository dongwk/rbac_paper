package com.app.web.authorization.manager.impl;

import com.app.web.authorization.config.Constants;
import com.app.web.authorization.manager.TokenManager;
import com.app.web.authorization.model.TokenModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 内存 Token
 * @see TokenManager
 * @date 2018/11/21
 */
@Component
public class MemTokenManager implements TokenManager {

    public static Map<String, TokenModel> tokens = new ConcurrentHashMap<>();

    public TokenModel createToken(long userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        //存储到redis并设置过期时间
        tokens.put(token, model);
        return model;
    }

    public TokenModel getToken(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        return new TokenModel();
    }

    public boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        return tokens.get(token) != null;
    }

    public void deleteToken(String token) {
        tokens.remove(token);
    }
}