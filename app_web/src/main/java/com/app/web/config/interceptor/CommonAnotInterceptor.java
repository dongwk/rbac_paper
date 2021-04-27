package com.app.web.config.interceptor;

import com.app.web.config.annotation.CommonAnot;
import com.app.web.controller.base.BaseController;
import com.app.web.common.R;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class CommonAnotInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod target = (HandlerMethod) handler;

        // 设置分页参数
        Object bean = target.getBean();
        BaseController baseBean = null;
        if (bean instanceof BaseController) {
            baseBean = (BaseController) bean;
            baseBean.setPage();
        }

        // 开启分页
        CommonAnot commonAnot = target.getMethod().getAnnotation(CommonAnot.class);
        if (commonAnot!= null) {
            if (bean instanceof BaseController) {
                if (commonAnot.enablePage()) {
                    baseBean.enablePage();
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod target = (HandlerMethod) handler;

        // 设置分页参数
        Object bean = target.getBean();

        // 分页处理
        Method method = target.getMethod();
        CommonAnot commonAnot = target.getMethod().getAnnotation(CommonAnot.class);
        if (commonAnot!= null) {
            if (bean instanceof BaseController) {
                if (method.getReturnType() == List.class) {
                    response.getOutputStream().write(96);
                }
            }
        }
    }
}