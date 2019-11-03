package com.app.web.config;

import com.app.web.authorization.interceptor.AuthorizationInterceptor;
import com.app.web.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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

    /**
     * MessageSource
     */
    @Autowired
    private MessageSource messageSource;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(this.messageSource);
        return validator;
    }
}