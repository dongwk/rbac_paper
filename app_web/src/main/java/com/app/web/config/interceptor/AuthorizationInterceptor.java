package com.app.web.config.interceptor;

import com.app.common.util.JsonUtil;
import com.app.core.common.ThrowBiz;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.manager.TokenManage;
import com.app.web.mo.LoginUserMo;
import com.app.web.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//    private final static Set<String> EXCLUDED_PATHS = new HashSet<>();
//    private final static Map<String, Set<HttpMethod>> EXCLUDED_PATHS_METHOD = new HashMap<>();
//
//    static{
//        EXCLUDED_PATHS_METHOD.put("/token", Sets.newHashSet(HttpMethod.POST));
//    }

    @Autowired
    private TokenManage tokenManage;

    @Override
    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 过滤某些方法
//        String uri = request.getRequestURI();
//        String method = request.getMethod();
//        if (EXCLUDED_PATHS_METHOD.containsKey(uri)) {
//            Set<HttpMethod> excludedSet = EXCLUDED_PATHS_METHOD.get(uri);
//            if (excludedSet == null) return true;
//            if (excludedSet.contains(HttpMethod.resolve(method))) return true;
//        }

        HandlerMethod target = (HandlerMethod) handler;

        // 验证注明了 Authorization 权限验证
        if (target.getMethod().getAnnotation(Authorization.class) != null) {

            // 截取参数 ? 之前的地址
            String uri = StringUtils.substringBefore(request.getRequestURI(), "?");

            // 从 header 中得到 token
            String token = TokenUtils.getToken(request);
            if (StringUtils.isBlank(token)) { // 登录信息没有传，请登录
                ThrowBiz.throwExce(HttpStatus.UNAUTHORIZED, "auth.token.exipred");
            }

            // 验证登录信息
            String val = tokenManage.get(token);
            if (StringUtils.isBlank(val)) { // 登录信息已过期或不存在，请重新登录
                ThrowBiz.throwExce(HttpStatus.UNAUTHORIZED, "auth.token.exipred");
            }

            LoginUserMo loginUserMo = JsonUtil.toBean(val, LoginUserMo.class);

            Set<String> funs = loginUserMo.getFuns();

            funs.contains(uri);
        }

        return true;
    }

//    private boolean isExcluded(String path, String requestMethod) {
//        boolean isHeadMethod = HttpMethod.HEAD.matches(requestMethod);
//        boolean isExcludedPath = EXCLUDED_PATHS.contains(path);
//        return isHeadMethod && isExcludedPath;
//    }
}