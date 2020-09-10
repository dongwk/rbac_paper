package com.app.web.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@MapperScan("com.app.mapper.mapper")
public class MybatisConfig {

}