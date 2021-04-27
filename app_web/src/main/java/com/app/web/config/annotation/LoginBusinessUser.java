package com.app.web.config.annotation;

import com.app.web.po.LoginUserPo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 * @author dongwk
 * @see com.app.web.config.interceptor.AuthorizationInterceptor
 * @see com.app.web.config.resolvers.LoginUserMethodArgumentResolver
 * @see LoginUserPo
 */
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginBusinessUser {
}