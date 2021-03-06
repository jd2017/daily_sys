package com.yun.chat.service;

import com.yun.chat.domain.Message;
import com.yun.mongodb.dao.ChatMongoRepositity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMongoRepositity chatMongoRepositity;
    public void sendMsg(Message msg){
//        messagingTemplate.convertAndSendToUser(msg.getSendTo(),"/queue/notifications",msg.getSenderName()+":"+msg.getMessage());
        chatMongoRepositity.save(msg);
//        messagingTemplate.setSendTimeout(1L);
        messagingTemplate.convertAndSendToUser(msg.getSendTo(), "/queue/notifications",msg);
    }

    public List<Message> queryHistoryMessage(String from, String to) {
        AggregationResults<Message> result = chatMongoRepositity.queryHistoryMessage(from,to);
        return result.getMappedResults();
    }
}
