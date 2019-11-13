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
     * @param mapperClass
     * @return
     */
    public static Class<?> extractModelClass(Class<?> mapperClass) {
        return extractModelClass(mapperClass, 0);
    }


    /**
     * 提取第二个泛型模型
     *
     * @param mapperClass
     * @return
     */
    public static Class<?> extractSecondModelClass(Class<?> mapperClass) {
        return extractModelClass(mapperClass, 1);
    }

    /**
     * 提取泛型模型
     *
     * @param mapperClass
     * @return
     */
    private static Class<?> extractModelClass(Class<?> mapperClass, int index) {
        Type[] types = mapperClass.getGenericInterfaces();
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
}
