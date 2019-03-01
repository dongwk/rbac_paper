package com.app.web.authorization.interceptor;

import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
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



    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 过滤某些方法
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if (EXCLUDED_PATHS_METHOD.containsKey(uri)) {
            Set<HttpMethod> excludedSet = EXCLUDED_PATHS_METHOD.get(uri);
            if (excludedSet == null) return true;
            if (excludedSet.contains(HttpMethod.resolve(method))) return true;
        }

        // 从 header 中得到 token
//        String authorization = request.getHeader (Constants.AUTHORIZATION);
        // 验证 token
//        TokenModel model = manager.getToken (authorization);
//        if (manager.checkToken (model)) {
//            // 如果 token 验证成功，将 token 对应的用户 id 存在 request 中，便于之后注入
//            request.setAttribute (Constants.CURRENT_USER_ID, model.getUserId ());
//            return true;
//        }
        // 如果验证 token 失败，并且方法注明了 Authorization，返回 401 错误
//        if (method.getAnnotation (Authorization.class) != null) {
//            response.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
            return true;
    }

    private boolean isExcluded(String path, String requestMethod) {
        boolean isHeadMethod = HttpMethod.HEAD.matches(requestMethod);
        boolean isExcludedPath = EXCLUDED_PATHS.contains(path);
        return isHeadMethod && isExcludedPath;
    }
}