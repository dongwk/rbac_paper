package com.app.web.config.resolvers;

import com.app.common.util.JsonUtil;
import com.app.web.config.annotation.LoginedUser;
import com.app.web.controller.manager.TokenManage;
import com.app.web.utils.HttpBizUtils;
import com.app.web.mo.LoginedUserMo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @date 2015/7/31.
 */
@Component
@Slf4j
public class LoginedUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private TokenManage tokenManage;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 如果参数类型是 UserVo 并且有 CurrentUser 注解则支持
        if (parameter.getParameterType ().isAssignableFrom (LoginedUserMo.class) &&
                parameter.hasParameterAnnotation (LoginedUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 取出鉴权时存入的登录用户 Id
        String token = HttpBizUtils.getToken(request);
        if (token != null) {
            String tokenVal = tokenManage.get(token);
            if (StringUtils.isNotBlank(tokenVal)) {
                return JsonUtil.toBean(tokenVal, LoginedUserMo.class);
            }

        }
        return null;
    }
}