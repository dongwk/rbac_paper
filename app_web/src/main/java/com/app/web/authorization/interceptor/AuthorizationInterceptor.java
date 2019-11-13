package com.app.web.authorization.interceptor;

import com.app.web.authorization.annotation.Authorization;
import com.app.web.authorization.config.Constants;
import com.app.web.authorization.manager.TokenManage;
import com.app.web.controller.exce.BizException;
import com.app.web.utils.HttpBizUtils;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final static Set<String> EXCLUDED_PATHS = new HashSet<>();
    private final static Map<String, Set<HttpMethod>> EXCLUDED_PATHS_METHOD = new HashMap<>();

    static{
        EXCLUDED_PATHS_METHOD.put("/token", Sets.newHashSet(HttpMethod.POST));
    }

    @Autowired
    private TokenManage tokenManage;

    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod target = (HandlerMethod) handler;

        // 过滤某些方法
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if (EXCLUDED_PATHS_METHOD.containsKey(uri)) {
            Set<HttpMethod> excludedSet = EXCLUDED_PATHS_METHOD.get(uri);
            if (excludedSet == null) return true;
            if (excludedSet.contains(HttpMethod.resolve(method))) return true;
        }

        // 验证注明了 Authorization 登录信息，返回 401 错误
        if (target.getMethod().getAnnotation(Authorization.class) != null) {

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

    private boolean isExcluded(String path, String requestMethod) {
        boolean isHeadMethod = HttpMethod.HEAD.matches(requestMethod);
        boolean isExcludedPath = EXCLUDED_PATHS.contains(path);
        return isHeadMethod && isExcludedPath;
    }
}