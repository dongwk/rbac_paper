package com.app.manage.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口自定义属性
 *
 * @author tianshu on 16/8/30 下午4:55.
 * @desc 参照 https://www.bbsmax.com/A/pRdB2yPdnx/
 *
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceProperty {

    /**
     * 接口超时时间,单位毫秒.默认值100毫秒
     *
     * @return 设置的超时时间
     */
    int timeout() default 400;

    /**
     * 当接口响应超时时,是否发送邮件.默认发送
     *
     * @return 返回ture需要发送邮件
     */
    boolean emailIfTimeout() default true;
}

