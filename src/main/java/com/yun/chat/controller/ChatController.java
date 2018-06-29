package com.yun.chat.controller;

import com.yun.chat.domain.Message;
import com.yun.daily.personUser.controller.PersonUserController;
import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/chat",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChatController {
    @Autowired
    private PersonUserService personUserService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Logger log = LoggerFactory.getLogger(ChatController.class);

    //进入聊天室页面  点对点
    @RequestMapping("/toChat")
    public String toChat(Map<String,Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        PersonUser personUser = personUserService.queryByAccount(userDetails.getUsername());
        model.put("person",personUser);
        //找出非当前登录用户的所有用户
        model.put("persons",personUserService.queryAllExceptCurrentUserByAccount(userDetails.getUsername()));
        return "websocket/chat";
    }

    //进入聊天室页面  点对点
    @MessageMapping("/chat")
    public void handleChat(Principal principal, Message msg){
        String from = principal.getName();
        PersonUser personUser = personUserService.queryByAccount(from);
        messagingTemplate.convertAndSendToUser(msg.getSendTo(),"/queue/notifications",personUser.getName()+":"+msg.getMessage());
    }

    /**
     * @MessageExceptionHandler(*Exception.class)
     * @MessageExceptionHandler({*1Exception.class,*2Exception.class})
     * @param t
     */
    @MessageExceptionHandler
    public void handleExceptions(Throwable t){
        log.error("ChatController-->error handling message:"+ t.getMessage());
    }
}
