package com.app.web.util;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author dongwk
 * @date 2019/5/6
 */
public class RestUtils {

    private final static String ASC = "asc";
    private final static String DESC = "desc";
    private final static String COMMA = ",";

    private final static String REST_DESC = "-";
    private final static String REST_ASC = "+";
    

    /**
     * 排序字段
     *
     * @author dongwk
     * @date 2018/12/4
     * @param sort 排序逗号分隔 -update_date, create_date
     * @return [{filed1, asc or desc}, {filed2, asc or desc}]
     */
    public static String[][] sortToArray(String sort){
        String[] fileds = StringUtils.isNoneEmpty(sort) ? StringUtils.split(sort.trim(), COMMA):null;
        if (fileds == null) return null;
        String[][] sortfileds = new String[fileds.length][2];

        for (int i = 0; i < fileds.length; i++) {
            String sortFiled = fileds[i];
            if (sortFiled.startsWith(REST_DESC)){
                sortfileds[i][0] = sortFiled.substring(1);
                sortfileds[i][1] = DESC;
            } else if (sortFiled.startsWith(REST_ASC)){
                sortfileds[i][0] = sortFiled.substring(1);
                sortfileds[i][1] = ASC;
            } else {
                sortfileds[i][0] = sortFiled;
                sortfileds[i][1] = ASC;
            }
        }

        return sortfileds;
    }

    /**
     * 排序字段
     *
     * @author dongwk
     * @date 2018/12/4
     * @param sort 排序逗号分隔 -update_date, create_date
     * @return update_date desc, create_date asc
     */
    public static String sortToSql(String sort){
        String[] fileds = StringUtils.isNotBlank(sort) ? StringUtils.split(sort.trim(), COMMA):null;
        if (fileds == null) return null;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < fileds.length; i++) {
            String sortFiled = fileds[i];
            if (sortFiled.startsWith(REST_DESC)){
                builder.append(COMMA).append(sortFiled.substring(1)).append(DESC);
            } else if (sortFiled.startsWith(REST_ASC)){
                builder.append(COMMA).append(sortFiled.substring(1)).append(ASC);
            } else {
                builder.append(COMMA).append(sortFiled).append(ASC);
            }
        }

        return builder.length() > 0 ? builder.deleteCharAt(0).toString() : "";
    }

    /**
     * 下换线转驼峰
     * @param fileds
     * @return
     */
    public static String filedsToCamel(String fileds) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(fileds))
            Arrays.stream(StringUtils.split(fileds.trim(), ",")).forEach(s -> builder.append(",").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)).append(" ").append(s));
        return builder.length() > 0 ? builder.substring(1) : "";
    }
}
