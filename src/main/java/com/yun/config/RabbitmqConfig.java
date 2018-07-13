package com.yun.config;

import com.yun.common.Constant;
import com.yun.common.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue myqueue() {
        return new Queue(Constant.QUEUE_RABBITMQ_LIVE_CHAT);
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue queueChat() {
        // true表示持久化该队列
        return new Queue(RabbitConstant.QUEUE_CHAT, true);
    }

    @Bean
    public Queue queueContract() {
        // true表示持久化该队列
        return new Queue(RabbitConstant.QUEUE_TOPIC, true);
    }

    @Bean
    public Queue queueQualification() {
        // true表示持久化该队列
        return new Queue(RabbitConstant.QUEUE_QUALIFICATION, true);
    }

    /**
     * 声明交互器
     *
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingTransaction() {
        return BindingBuilder.bind(queueChat()).to(directExchange()).with(RabbitConstant.RK_CHAT);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingContract() {
        return BindingBuilder.bind(queueContract()).to(directExchange()).with(RabbitConstant.RK_TOPIC);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingQualification() {
        return BindingBuilder.bind(queueQualification()).to(directExchange()).with(RabbitConstant.RK_QUALIFICATION);
    }
}
