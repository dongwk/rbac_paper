package com.app.web.mq.service;

import com.app.common.util.JsonUtil;
import com.app.web.config.properties.RabbitMQProperties;
import com.app.web.mq.dto.LoginSucDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQProperties rabbitMQProperties;

    public void loginSuccess(LoginSucDto loginSucDto){
        try {
            System.out.println("Send login success msg  " + loginSucDto);
            // rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), rabbitMQProperties.getRoutingKey(), loginSucDto);
        } catch (Exception e){
            log.error("login success send msg fail: {}, {}", JsonUtil.toJson(loginSucDto), ExceptionUtils.getStackTrace(e));
        }
    }
}
