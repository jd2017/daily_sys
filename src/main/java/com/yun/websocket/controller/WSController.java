package com.yun.websocket.controller;

import com.sun.xml.internal.ws.dump.LoggingDumpTube;
import com.yun.websocket.domain.WSMessage;
import com.yun.websocket.domain.WSResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WSController {

    @RequestMapping("/ws")
    public String ws(){
        System.out.println("------------");
        return "/websocket/ws";
    }

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WSResponse say(WSMessage message) throws Exception {
//        Thread.sleep(3000);
        return new WSResponse("Welcome，"+message.getName()+"!");
    }
}
