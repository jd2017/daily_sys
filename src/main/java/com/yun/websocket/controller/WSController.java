package com.yun.websocket.controller;

import com.yun.websocket.domain.WSMessage;
import com.yun.websocket.domain.WSResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WSController {
    //进入广播式页面
    @RequestMapping("/ws")
    public String ws(){
        System.out.println("------------");
        return "/websocket/ws";
    }

    //发送广播式消息
    @MessageMapping("/welcome")//当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址类似requestMepping
    @SendTo("/topic/getResponse")//当服务端有消息时会对订阅了@SendTo中的路径的浏览器发送消息
    public WSResponse say(WSMessage message) throws Exception {
//        Thread.sleep(3000);
        return new WSResponse("Welcome，"+message.getName()+"!");
    }

}
