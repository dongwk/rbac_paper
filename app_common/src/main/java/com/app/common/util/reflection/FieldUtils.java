package com.app.common.util.reflection;

import java.lang.reflect.Field;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 14:17
 */
public class FieldUtils {

    private static final Class<Object> objectClazz = Object.class;

    public static final boolean isExist(Class<?> clazz, String name){
        if (clazz == null) return false;
        Class<?> superClazz = clazz.getSuperclass();
        try {
            clazz.getDeclaredField(name);
            return true;
        } catch (NoSuchFieldException e) {
            if (superClazz != objectClazz) {
                return isExist(superClazz, name);
            }
            return false;
        }
    }

    public static final Field getField(Class<?> clazz, String name){
        if (clazz == null) return null;
        Class<?> superClazz = clazz.getSuperclass();
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (superClazz != objectClazz) {
                return getField(superClazz, name);
            }
            return null;
        }
    }
}
