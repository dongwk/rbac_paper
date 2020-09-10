package com.app.common.util;

import com.app.common.PageCount;
import com.app.common.util.reflection.FieldUtils;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(FieldUtils.isExist(PageCount.class, "username"));
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
