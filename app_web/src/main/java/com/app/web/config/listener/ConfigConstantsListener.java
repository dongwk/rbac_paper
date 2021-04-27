package com.app.web.config.listener;

import com.app.core.thread.MultiThreadTaskUtils;
import com.app.web.constant.ConfigConstants;
import com.app.service.service.ConfigConstantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;

@Slf4j
public class ConfigConstantsListener implements
        ApplicationListener<ContextRefreshedEvent> {

    /**
     * 登录校验
     */
    @Autowired
    private ConfigConstantService configConstantService;

    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            ConfigConstants.loadConfigConstant(configConstantService.list());
        } catch (Exception e) {
            log.error("加载 config_constants 数据失败", e);
        }

        try {
            MultiThreadTaskUtils.setThreadPoolTaskExecutor(threadPoolTaskExecutor);
        } catch (Exception e) {
            log.error("加载线程池失败", e);
        }
    }
}