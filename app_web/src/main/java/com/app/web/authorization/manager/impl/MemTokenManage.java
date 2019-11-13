package com.app.web.authorization.manager.impl;

import com.app.web.authorization.manager.TokenManage;
import com.app.web.authorization.model.TokenModel;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.MapMaker;
import com.google.common.graph.Graph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 内存 Token
 * @see TokenManage
 * @date 2018/11/21
 */
@Slf4j
@Component("memTokenManage")
public class MemTokenManage implements TokenManage {

    private Cache<String, String> tokens = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public void add(String token, String val) {
        //存储到redis并设置过期时间
        tokens.put(token, val);
    }

    public String get(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        return tokens.getIfPresent(token);

    }

    public boolean exists(String token) {
        if (token == null) {
            return false;
        }
        return get(token) != null;
    }

    public void del(String token) {
        tokens.invalidate(token);
    }
}