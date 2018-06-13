package com.yun.config;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQImpl;
import com.yun.common.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue Queue() {
        return new Queue(Constant.QUEUE_RABBITMQ_LIVE_CHAT);
    }
}
