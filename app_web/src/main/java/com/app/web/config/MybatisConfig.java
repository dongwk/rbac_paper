package com.app.web.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@MapperScan("com.app.mapper.mapper")
public class MybatisConfig {


//    @Bean("mybatisSqlSession")
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader, GlobalConfiguration globalConfiguration) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setTypeAliasesPackage("com.app.model");
//        sqlSessionFactory.setGlobalConfig(globalConfiguration);
//        return sqlSessionFactory.getObject();
//    }
//
//    @Bean
//    public GlobalConfiguration globalConfiguration() {
//        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
//        conf.setLogicDeleteValue("-1");
//        conf.setLogicNotDeleteValue("1");
//        conf.setIdType(0);
//        conf.setMetaObjectHandler(new MyMetaObjectHandler());
//        return conf;
//    }
}