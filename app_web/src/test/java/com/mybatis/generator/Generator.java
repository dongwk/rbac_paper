/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */

package com.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成不了表，用终端执行命令，会报详细错误信息
 * java -jar "D:\Program Files\repository\org\mybatis\generator\mybatis-generator-core\1.3.2\mybatis-generator-core-1.3.2.jar" -configfile D:\project\work\longka_community\api\src\test\resources\generatorConfig-oracle.xml
 */
public class Generator {

    public static void main( String[] args ) {
        try {
            List<String> warnings = new ArrayList<>();

            Class clazz = Generator.class;
            InputStream inputStream = clazz.getResourceAsStream("/generatorConfig-mybatis.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(inputStream);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}