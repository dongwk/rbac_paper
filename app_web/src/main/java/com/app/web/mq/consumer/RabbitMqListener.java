package com.app.web.mq.consumer;

import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    public void listen(byte[] message) {
        String msg = new String(message);
        System.out.println(msg);
    }
}