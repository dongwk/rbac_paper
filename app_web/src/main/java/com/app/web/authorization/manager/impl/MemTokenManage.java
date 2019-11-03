package com.app.web.authorization.manager.impl;

import com.app.web.authorization.manager.TokenManage;
import com.app.web.authorization.model.TokenModel;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.MapMaker;
import com.google.common.graph.Graph;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 内存 Token
 * @see TokenManage
 * @date 2018/11/21
 */
@Component("memTokenManage")
public class MemTokenManage implements TokenManage {

    private LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, String>() {
                        public String load(String key) throws AnyException {
                            return createExpensiveGraph(key);
                        }
                    });

    public void storeToken(String token, String val, int expires) {
        //存储到redis并设置过期时间
        tokens.put(token, val);
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