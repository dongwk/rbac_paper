package com.app.web.controller.manager.impl;

import com.app.web.controller.manager.TokenManage;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    @Override
    public void add(String token, String val) {
        //存储到redis并设置过期时间
        tokens.put(token, val);
    }

    @Override
    public String get(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        return tokens.getIfPresent(token);

    }

    @Override
    public boolean exists(String token) {
        if (token == null) {
            return false;
        }
        return get(token) != null;
    }

    @Override
    public void del(String token) {
        tokens.invalidate(token);
    }

    @Override
    public void refresh(String token) {
        String val = get(token);
        tokens.put(token, val);
    }
}