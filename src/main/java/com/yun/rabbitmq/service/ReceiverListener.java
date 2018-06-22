package com.yun.rabbitmq.service;

import com.yun.common.Constant;
import com.yun.rabbitmq.domain.MyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ReceiverListener {
//    @RabbitListener(queues = Constant.QUEUE_RABBITMQ_LIVE_CHAT)
//    public void receiver(String message) {
//        System.out.println("收到<"+message+">");
//    }

    @RabbitListener(queues = Constant.QUEUE_RABBITMQ_LIVE_CHAT)
    @RabbitHandler
    public void receiver(MyMessage myMessage) {
       System.out.println("收到<"+myMessage.getSender()+"发来的消息："+myMessage.getMsg()+">");
    }
}
