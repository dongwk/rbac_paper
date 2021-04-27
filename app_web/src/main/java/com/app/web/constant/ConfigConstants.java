package com.app.web.constant;

import com.app.model.model.ConfigConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 常量
 * @author dongwk
 * @date 2015/7/31.
 */
public class ConfigConstants {

    private static final Map<String, List<String>> map = Maps.newHashMap();

    public static List<String> get(String key) {
        return map.get(key);
    }

    public static String getOne(String key) {
        List<String> list = map.get(key);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public static void loadConfigConstant(List<ConfigConstant> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (ConfigConstant configConstant : list) {
                String key = StringUtils.trim(configConstant.getConstantType());
                if (StringUtils.isNotBlank(key)) {
                    List<String> values = map.get(key);
                    if (CollectionUtils.isEmpty(values)) {
                        values = Lists.newArrayList();
                        map.put(key, values);
                    }
                    values.add(configConstant.getConstantValue());
                }
            }
        }
    }
}