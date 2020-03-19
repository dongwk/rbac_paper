package com.app.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class TokenUtils {

    public static String generateToken(){
        return UUID.randomUUID().toString();
    }

    public static String getToken(HttpServletRequest request){
        // 从 header 中得到 token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(token)) {
            return token.trim().replace("Bearer ", "");
        }
        return token;
    }
}
