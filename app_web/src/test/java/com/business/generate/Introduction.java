/*
 * Copyright (C) 1997-2021 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */

package com.business.generate;

import com.business.generate.core.TemplateInfo;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * <B>Description:</B> 要生成的描述 <br>
 * <B>Create on:</B> 2021-04-16 14:31 <br>
 * 以表为基础生成代码
 * @author wenkai.dong
 * @version 1.0
 */
public class Introduction {

    // 模板路径
    public static String templatePath = "D:\\project\\work\\rbac_paper\\app_web\\src\\test\\java\\com\\business\\generate\\template";
    // 模板后缀
    public static String templateExt = ".ftl";
    // 实体类包
    public static String beanPackage = "com.business.generate.model";
    // 输出目录
    public static String targetPath = "d:\\com\\g";

    // 模板数据
    public static String ATTR_BEAN_PACKAGE = "beanPackage";
    public static String ATTR_BEAN_NAME = "beanName";
    public static String ATTR_ATTRIBUTES = "attributes";
    public static String ATTR_BEAN = "bean";

    // 模板信息
    public static Map<String, TemplateInfo> templateInfoMap = Maps.newLinkedHashMap();
    static {
        templateInfoMap.put("beanDao.ftl", new TemplateInfo("com", ".java"));
    }

    //

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Attribute {
        private String name;
        private String javaType;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BeanInfo {
        private String path;
        private String outExt;
    }



    enum FileTypeEnum {
        MODEL, // 实体类
        DAO, // dao
        SERVICE, // service
        CONTROLLER, // controller
    }

    // 模板用到的源数据
    class Attr {
        private Table table; // table 属性
        private Bean bean; // bean 属性
    }

    // 模板
    class Template {
        private String sourcePath; // 模板路径
        private String outputPath; // 输出目录
        private FileTypeEnum fileTypeEnum; // 文件类型

        // 针对Java文件
        private String packageName; // 包名
        private String packages; // 包名
        private String imports; // 导入的类
    }

    // 数据库相关
    public static String dbDriverClass = "com.mysql.cj.jdbc.Driver"; // 数据库驱动
    public static String dbConnectionURL = "jdbc:mysql://127.0.0.1:3306/rbac_paper?allowMultiQueries=true&autoCommit=false&useUnicode=true&zeroDateTimeBehavior=convertToNull&characterEncoding=utf8&serverTimezone=UTC"; // 数据库地址
    public static String dbUser = "root"; // 数据库用户名
    public static String dbPassword = "root"; // 数据库密码

    // 输出内容
    private String fileName; // 文件名
    private String fileExtension; // 文件扩展名
    private Map<String, Object>  templat; // 文件所用的数据

    // 输入内容
    // 表名
    // 字段名
    // 字段类型


    // 配置
    // 数据库相关

    // 实体
    private List<Table> tables; // 表

    class Bean {
        private String name; // bean name
        private String reference; // 完整的包名
        private String packageName; // 包名

    }

    class Table {
        private String tableName; // 表名
        private String pk; // 主键
        private List<Column> columns; // 字段
    }

    class Column {
        private String columnName; // 表名
        private String pk; // 主键
    }
}
