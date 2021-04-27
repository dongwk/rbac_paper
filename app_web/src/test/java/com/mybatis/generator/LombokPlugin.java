package com.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Liweizhou  2018/6/6
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加domain的import
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableName");
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("lombok.ToString");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("lombok.experimental.Accessors");
        topLevelClass.addImportedType("com.app.model.base.BaseModel");

        //添加domain的注解
        topLevelClass.addAnnotation("@Accessors(chain = true)");
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@ToString");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");

        topLevelClass.addJavaDocLine("@TableName(\""+introspectedTable.getFullyQualifiedTableNameAtRuntime()+"\")");
        topLevelClass.setSuperClass("BaseModel");


//
//        //添加domain的注释
//        topLevelClass.addJavaDocLine("/**");
//        topLevelClass.addJavaDocLine("* Created by Mybatis Generator on " + date2Str(new Date()));
//        topLevelClass.addJavaDocLine("*/");

        return true;
    }


    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if (field.getName().equalsIgnoreCase("id")
                || field.getName().equalsIgnoreCase("status")
                || field.getName().equalsIgnoreCase("createDate")
                || field.getName().equalsIgnoreCase("updateDate")) {
            return false;
        }

        if (field.getType().getShortName().equals("Long")) {
            field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
        }

        return true;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}