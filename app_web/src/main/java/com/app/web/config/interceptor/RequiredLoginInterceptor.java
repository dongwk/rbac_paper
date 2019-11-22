package com.app.web.config.interceptor;

import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.RequiredLogin;
import com.app.web.controller.exce.BizException;
import com.app.web.controller.manager.TokenManage;
import com.app.web.utils.HttpBizUtils;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RequiredLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManage tokenManage;

    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod target = (HandlerMethod) handler;

        // 验证注明了 RequiredLogin Authorization 登录验证
        if (target.getMethod().getAnnotation(RequiredLogin.class) != null || target.getMethod().getAnnotation(Authorization.class) != null) {

            // 从 header 中得到 token
            String token = HttpBizUtils.getToken(request);

            // 验证 token
            String val = tokenManage.get(token);
            if (StringUtils.isBlank(val)) { // 登录信息已过期或不存在，请重新登录
                throw new BizException(HttpStatus.UNAUTHORIZED, "auth.token.exipred");
            }
        }
        return true;
    }
}