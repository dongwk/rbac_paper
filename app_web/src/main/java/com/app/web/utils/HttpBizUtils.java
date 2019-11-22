package com.app.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 获取 token
 *
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:30
 */
public class HttpBizUtils {

    public static String getToken(HttpServletRequest request){
        // 从 header 中得到 token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(token)) {
            return token.trim().replace("Bearer ", "");
        }
        return token;
    }
}
