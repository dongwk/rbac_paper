package com.app.web.mo.util;

import com.app.common.util.date.DateUtil;
import com.app.common.util.reflection.GenericUtils;
import com.app.model.base.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author wenkai.dong
 * @date 2019/11/30
 */
@Slf4j
public class ToEntity<T extends BaseModel> {
    private Class<T> tClass =  (Class<T>) GenericUtils.getSuperclassGeneric(getClass());
}
