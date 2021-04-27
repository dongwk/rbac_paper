package com;

import com.app.common.PageCount;
import com.app.common.util.reflection.FieldUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    @Test
    public void testBigDecimal(){
        BigDecimal d = new BigDecimal(0.01);
        d = d.add(new BigDecimal(0.021));
        System.out.println(BigDecimal.valueOf(0.2));
    }

    class Student<T> {
        private T t;
    }
}
