package com.app.web.config;

import com.app.web.authorization.interceptor.AuthorizationInterceptor;
import com.app.web.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置类，增加自定义拦截器和解析器
 * @see CurrentUserMethodArgumentResolver
 * @see AuthorizationInterceptor
 * @author ScienJus
 * @date 2015/7/30.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 权限
     */
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    /**
     * 当前登录用户
     */
    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
}