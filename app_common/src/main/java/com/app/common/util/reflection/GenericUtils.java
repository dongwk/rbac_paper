package com.app.common.util.reflection;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * 获取泛型类型
 * @author dongwk
 * @version 1.0
 * @date 2018/11/29 16:59
 */
public class GenericUtils {

    /**
     * 提取泛型模型
     *
     * @param clazz
     * @return
     */
    public static Class<?> getInterfaceGeneric(Class<?> clazz) {
        return getInterfaceGeneric(clazz, 0);
    }

    /**
     * 提取第二个泛型模型
     *
     * @param clazz
     * @return
     */
    public static Class<?> getInterfaceSecondGeneric(Class<?> clazz) {
        return getInterfaceGeneric(clazz, 1);
    }


    /**
     * 提取泛型模型
     *
     * @param clazz
     * @return
     */
    public static Class<?> getSuperclassGeneric(Class<?> clazz) {
        return getSuperclassGeneric(clazz, 0);
    }

    /**
     * 提取第二个泛型模型
     *
     * @param clazz
     * @return
     */
    public static Class<?> getSuperclassSecondGeneric(Class<?> clazz) {
        return getSuperclassGeneric(clazz, 1);
    }

    /**
     * 提取接口泛型模型
     *
     * @param clazz
     * @return
     */
    private static Class<?> getInterfaceGeneric(Class<?> clazz, int index) {
        Type[] types = clazz.getGenericInterfaces();
        ParameterizedType target = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                Type[] typeArray = ((ParameterizedType) type).getActualTypeArguments();
                if(ArrayUtils.isNotEmpty(typeArray)){
                    for (Type t:typeArray) {
                        if(t instanceof TypeVariable || t instanceof WildcardType){
                            target = null;
                            break;
                        }else {
                            target = (ParameterizedType) type;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return target == null ? null : (Class<?>) target.getActualTypeArguments()[index];
    }

    /**
     * 提取父类泛型模型
     *
     * @param clazz
     * @return
     */
    private static Class<?> getSuperclassGeneric(Class<?> clazz, int index) {
        Type type = clazz.getGenericSuperclass();

        ParameterizedType target = null;
        if (type instanceof ParameterizedType) {
            Type[] typeArray = ((ParameterizedType) type).getActualTypeArguments();
            if(ArrayUtils.isNotEmpty(typeArray)){
                for (Type t:typeArray) {
                    if(t instanceof TypeVariable || t instanceof WildcardType){
                        target = null;
                    }else {
                        target = (ParameterizedType) type;
                    }
                }
            }
        }
        return target == null ? null : (Class<?>) target.getActualTypeArguments()[index];
    }
}
