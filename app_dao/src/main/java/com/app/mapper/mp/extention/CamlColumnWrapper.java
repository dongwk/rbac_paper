package com.app.mapper.mp.extention;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Rest 字段支持，驼峰字段转下划线
 * @param <T>
 */
public class CamlColumnWrapper<T> extends EntityWrapper<T> {

    @Override
    public Wrapper setSqlSelect(String sqlSelect) {
        if (StringUtils.isNotBlank(sqlSelect)) {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(StringUtils.split(sqlSelect, ",")).forEach(s -> builder.append(",").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)).append(" ").append(s));
            sqlSelect = builder.substring(1);
        }
        return super.setSqlSelect(sqlSelect);
    }

    @Override
    public Wrapper<T> orderBy(boolean condition, String columns) {
        if (StringUtils.isNotBlank(columns)) {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(StringUtils.split(columns, ",")).forEach(s -> builder.append(",").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)));
            columns = builder.substring(1);
        }
        return super.orderBy(condition, columns);
    }

}
