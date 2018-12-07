package com.app.web.util.annotation;

import java.lang.annotation.*;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:38
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnSupportQuery {
}
