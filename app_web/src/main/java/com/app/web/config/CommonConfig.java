package com.app.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class CommonConfig {

    @Autowired
    private RejectedExecutionHandler rejectedExecutionHandler;

    @Bean
    public RejectedExecutionHandler rejectedExecutionHandler() {
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        return rejectedExecutionHandler;
    }

    @Bean
    @DependsOn("rejectedExecutionHandler")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5); // 线程池维护线程的最少数量
        threadPoolTaskExecutor.setKeepAliveSeconds(200); // 允许的空闲时间
        threadPoolTaskExecutor.setMaxPoolSize(5); // 线程池维护线程的最大数量
        threadPoolTaskExecutor.setQueueCapacity(5); // 缓存队列
        threadPoolTaskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler); // 对拒绝task的处理策略
        return threadPoolTaskExecutor;
    }
}