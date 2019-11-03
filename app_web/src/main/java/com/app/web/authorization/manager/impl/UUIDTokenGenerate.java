package com.app.web.authorization.manager.impl;

import com.app.web.authorization.manager.TokenGenerate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDTokenGenerate implements TokenGenerate {

    @Override
    public String createToken() {
        return UUID.randomUUID().toString();
    }
}
