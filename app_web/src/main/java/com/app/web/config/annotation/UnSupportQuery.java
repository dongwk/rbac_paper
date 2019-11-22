package com.app.web.config.annotation;

import java.lang.annotation.*;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:38
 * @description 统一的查询入口，哪些字段不支持查询
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnSupportQuery {
}
