package com.yun.config;

import com.yun.common.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue myqueue() {
        return new Queue(Constant.QUEUE_RABBITMQ_LIVE_CHAT);
    }
}
