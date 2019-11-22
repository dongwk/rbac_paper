package com.app.web.config;

import com.app.web.config.interceptor.AuthorizationInterceptor;
import com.app.web.config.interceptor.RequiredLoginInterceptor;
import com.app.web.config.resolvers.LoginedUserMethodArgumentResolver;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置类，增加自定义拦截器和解析器
 * @see LoginedUserMethodArgumentResolver
 * @see AuthorizationInterceptor
 * @author ScienJus
 * @date 2015/7/30.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 登录校验
     */
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    /**
     * 权限校验
     */
    @Autowired
    private RequiredLoginInterceptor requiredLoginInterceptor;

    /**
     * 自定义登录用户参数
     */
    @Autowired
    private LoginedUserMethodArgumentResolver loginedUserMethodArgumentResolver;

    /**
     * MessageSource
     */
    @Autowired
    private MessageSource messageSource;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requiredLoginInterceptor);
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginedUserMethodArgumentResolver);
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(this.messageSource);
        return validator;
    }
}