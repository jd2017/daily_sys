package com.yun.websocket.controller;

import com.yun.chat.domain.Message;
import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import com.yun.websocket.domain.WSMessage;
import com.yun.websocket.domain.WSResponse;
import com.yun.websocket.service.TopicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class WSController {
    @Autowired
    private PersonUserService personUserService;
    @Autowired
    private TopicMessageService topicMessageService;

    //进入广播式页面
    @RequestMapping("/ws")
    public String ws(){
        System.out.println("------------");
        return "/websocket/ws";
    }

    //进入广播式页面
    @RequestMapping("/topic")
    public String topictopic(){
        System.out.println("------------");
        return "/websocket/topic";
    }

    //发送广播式消息
    @MessageMapping("/welcome")//当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址类似requestMepping
    @SendTo("/topic/getResponse")//当服务端有消息时会对订阅了@SendTo中的路径的浏览器发送消息
    public WSResponse say(WSMessage message) throws Exception {
//        Thread.sleep(3000);
        return new WSResponse("Welcome，"+message.getName()+"!");
    }

    //发送广播式消息
    @MessageMapping("/sendMessage")//当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址类似requestMepping
    @SendTo("/topic/messages")//当服务端有消息时会对订阅了@SendTo中的路径的浏览器发送消息
    public Message sendMessage(Principal principal, String message) throws Exception {
        String from = principal.getName();
        PersonUser personUser = personUserService.queryByAccount(from);
        Message topicMessage = new Message();
        topicMessage.setSender(from);
        topicMessage.setSenderName(personUser.getName());
        LocalDateTime sendTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        topicMessage.setMessage(message);
        topicMessage.setSendTime(dtf.format(sendTime));
        topicMessageService.sendMsg(topicMessage);
        return topicMessage;
    }
}
