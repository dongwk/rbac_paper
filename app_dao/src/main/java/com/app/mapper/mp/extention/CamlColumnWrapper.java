package com.app.mapper.mp.extention;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Rest 字段支持，驼峰字段转下划线
 * @param <T>
 */
public class CamlColumnWrapper<T> extends QueryWrapper<T> {

    public Wrapper setSqlSelect(String sqlSelect) {
        if (StringUtils.isNotBlank(sqlSelect)) {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(StringUtils.split(sqlSelect, ",")).forEach(s -> builder.append(",").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)).append(" ").append(s));
            sqlSelect = builder.substring(1);
        }
        // TODO
        return this;
    }

    @Override
    public QueryWrapper<T> orderBy(boolean condition, boolean isAsc, String... columns) {
        if (columns != null && columns.length > 0) {
            StringBuilder builder = new StringBuilder();
//            Arrays.stream(StringUtils.split(columns, ",")).forEach(s -> builder.append(",").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)));
//            columns = builder.substring(1);
        }
        // TODO
        return super.orderBy(condition, isAsc, columns);
    }

}
