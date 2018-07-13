package com.yun.rabbitmq.service;

import com.yun.common.JsonResponse;
import com.yun.common.RabbitConstant;
import com.yun.common.ResponseCode;
import com.yun.rabbitmq.domain.MyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
    @Autowired
    private RabbitTemplate rabbit;
    public JsonResponse send(String account, String message) {
//        rabbit.convertAndSend(Constant.QUEUE_RABBITMQ_LIVE_CHAT,account+"发来消息："+message);
        MyMessage myMessage = new MyMessage();
        myMessage.setMsg(message);
        myMessage.setSender(account);
        rabbit.convertAndSend(RabbitConstant.EXCHANGE,RabbitConstant.RK_TOPIC,myMessage);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage("成功");
        jsonResponse.setCode(ResponseCode.SUCCESS);
        return jsonResponse;
    }
}
