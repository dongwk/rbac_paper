package com.app.web.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限验证
 * 这个也支持登录验证
 * 在Controller的方法上使用此注解，该方法在映射时会检查用户是否有权限，无权限返回403错误
 * 403 状态码解释 http://www.androidchina.net/6013.html
 * @see com.app.web.config.interceptor.AuthorizationInterceptor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization{

    // 路径地址
    String value() default "";
}