package com.app.web.utils;

import com.app.common.util.date.DateUtil;
import com.app.model.base.BaseModel;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author wenkai.dong
 * @date 2019/12/6
 */
public class MoToDoUtils {

    /**
     * 转换为新增对象
     * @param orig
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends BaseModel> T toAddDO(Object orig, Class<T> clazz){
        if (orig == null || clazz == null) return null;

        T obj = null;
        try {
            obj = clazz.newInstance();
            BeanUtils.copyProperties(obj, orig);
            Date date = DateUtil.now();
            obj.setCreateDate(date);
            obj.setUpdateDate(date);
            obj.setStatus(true);
        } catch (InstantiationException e) {
            throw new RuntimeException(clazz.getCanonicalName() + " 无默认的构造函数");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("转换类型异常", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("拷贝属性异常", e);
        }
        return obj;
    }

    /**
     * 转换为更新对象，不设置ID，创建时间，状态
     * @param orig
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends BaseModel> T toUpdDO(Object orig, Class<T> clazz){
        if (orig == null || clazz == null) return null;

        T obj = null;
        try {
            obj = clazz.newInstance();
            BeanUtils.copyProperties(obj, orig);
            Date date = DateUtil.now();
            obj.setUpdateDate(date);
        } catch (InstantiationException e) {
            throw new RuntimeException(clazz.getCanonicalName() + " 无默认的构造函数");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("转换类型异常", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("拷贝属性异常", e);
        }
        return obj;
    }
}
