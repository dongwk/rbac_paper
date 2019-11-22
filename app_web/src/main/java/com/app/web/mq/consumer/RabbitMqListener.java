package com.app.web.mq.consumer;

import com.app.common.util.JsonUtil;
import com.app.web.config.properties.RabbitMQProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    public void listen(byte[] message) {
        String msg = new String(message);
        System.out.println(msg);
    }
}