package com.app.common.util;

import com.app.common.util.reflection.FieldUtils;
import com.app.model.model.User;
import com.app.service.service.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 14:23
 */
public class ReflectionTest {

    /**
     * 测试有没有字段的判断
     * @author dongwk
     * @date 2018/12/6
     */
    @Test
    public void testField(){
        Assert.assertTrue(FieldUtils.isExist(User.class, "username"));
    }

    /**
     * 测试获取泛型
     * @author dongwk
     * @date 2018/12/6
     */
    @Test
    public void testGeneric(){

    }

    class Student<T> {
        private T t;
    }
}
