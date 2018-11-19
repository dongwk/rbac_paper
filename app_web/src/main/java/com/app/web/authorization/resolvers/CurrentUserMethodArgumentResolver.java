package com.app.web.authorization.resolvers;

import com.app.web.authorization.annotation.CurrentUser;
import com.app.web.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
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
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 如果参数类型是 UserVo 并且有 CurrentUser 注解则支持
        if (parameter.getParameterType ().isAssignableFrom (UserVo.class) &&
                parameter.hasParameterAnnotation (CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 取出鉴权时存入的登录用户 Id
        // TODO 获取 token
        String token = null;
        if (token == null) {
            // 从 redis 取
            return new UserVo("lisi");
        }
        return null;
    }
}