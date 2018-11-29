/*
package com.ef.golf.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

*/
/**
 * com.ef.golf.rabbitmq
 * Administrator
 * 2018/10/16 15:21
 *//*

@Service
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendQueue(String exchange_key, String queue_key, Object object) {
        // convertAndSend 将Java对象转换为消息发送至匹配key的交换机中Exchange
        amqpTemplate.convertAndSend(exchange_key, queue_key, object);
    }
}
*/
