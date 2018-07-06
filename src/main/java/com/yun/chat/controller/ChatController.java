package com.yun.chat.controller;

import com.yun.chat.domain.Message;
import com.yun.chat.service.ChatService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/chat",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChatController {
    @Autowired
    private PersonUserService personUserService;
    @Autowired
    private ChatService chatService;

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
        msg.setSender(from);
        msg.setSenderName(personUser.getName());
        LocalDateTime sendTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        msg.setSendTime(dtf.format(sendTime));
        chatService.sendMsg(msg);
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

    @RequestMapping("/queryHistoryMessage")
    @ResponseBody
    public List<Message> queryHistoryMessage(Principal principal, String to){
        String from = principal.getName();
        return chatService.queryHistoryMessage(from,to);
    }
}
