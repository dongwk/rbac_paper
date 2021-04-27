package com.app.web.config.interceptor;

import com.app.web.config.annotation.WechatAuthorization;
import com.app.web.controller.manager.TokenManage;
import com.app.web.utils.TokenUtils;
import com.app.core.common.ThrowBiz;
import com.app.core.enums.MessagesPropertiesEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wechat 授权校验
 */
@Component
public class WechatAuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManage tokenManage;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod target = (HandlerMethod) handler;

        // 验证注明了 RequiredLogin Authorization 登录验证
        if (target.getMethod().getAnnotation(WechatAuthorization.class) != null) {

            // 从 header 中得到 token
            String token = TokenUtils.getToken(request);

            // 验证 token
            String val = tokenManage.get(token);
            if (StringUtils.isBlank(val)) { // 登录信息已过期或不存在，请重新登录
                 ThrowBiz.throwExce(HttpStatus.UNAUTHORIZED, MessagesPropertiesEnum.AUTH_TOKEN_EXIPRED);
            }
        }
        return true;
    }
}