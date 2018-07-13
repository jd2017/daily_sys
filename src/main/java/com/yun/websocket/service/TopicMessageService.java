package com.yun.websocket.service;

import com.yun.chat.domain.Message;
import com.yun.common.RabbitConstant;
import com.yun.mongodb.dao.ChatMongoRepositity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicMessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ChatMongoRepositity chatMongoRepositity;
    public void sendMsg(Message topicMessage){
        chatMongoRepositity.save(topicMessage,"topicmessage");
    }

    public List<Message> queryHistoryMessage(String from, String to) {
        AggregationResults<Message> result = chatMongoRepositity.queryHistoryMessage(from,to);
        return result.getMappedResults();
    }
}
