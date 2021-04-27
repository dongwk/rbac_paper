package com.app.web.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**  自定义填充公共 name 字段  */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * <p>
     * 插入元对象字段填充
     * </p>
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object status = getFieldValByName("status", metaObject);
        Object createDate = getFieldValByName("createDate", metaObject);
        Object updateDate = getFieldValByName("updateDate", metaObject);
        if (status == null) {
            setFieldValByName("status", true, metaObject);
        }
        if (createDate == null) {
            setFieldValByName("createDate", new Date(), metaObject);
        }
        if (updateDate == null) {
            setFieldValByName("updateDate", new Date(), metaObject);
        }
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     * Created with IntelliJ IDEA.
     * Author:  Wu Yujie
     * Email:  coffee377@dingtalk.com
     * Time:  2017/04/16 15:03
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateDate = getFieldValByName("updateDate", metaObject);
        if (updateDate == null) {
            setFieldValByName("updateDate", new Date(), metaObject);
        }
    }
}