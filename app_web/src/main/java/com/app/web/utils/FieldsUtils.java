package com.app.web.utils;

import com.app.common.util.reflection.FieldUtils;
import com.app.web.config.annotation.UnSupportQuery;

import java.lang.reflect.Field;

/**
 *
 * 字段是否可以查询
 *
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:30
 */
public class FieldsUtils {

    public static boolean isQuery(Class<?> clazz, String name){
        Field field = FieldUtils.getField(clazz, name);
        if (field == null) return false;
        return !field.isAnnotationPresent(UnSupportQuery.class);
    }
}
