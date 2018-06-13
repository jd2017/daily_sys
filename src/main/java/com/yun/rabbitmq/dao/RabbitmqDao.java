package com.yun.rabbitmq.dao;

import com.yun.common.Constant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RabbitmqDao {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        this.rabbitTemplate.convertAndSend(Constant.QUEUE_RABBITMQ_LIVE_CHAT, msg);
    }
}
