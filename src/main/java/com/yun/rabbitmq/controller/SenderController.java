package com.yun.rabbitmq.controller;

import com.yun.chat.controller.ChatController;
import com.yun.common.JsonResponse;
import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import com.yun.rabbitmq.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/rabbitmq",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SenderController {
    @Autowired
    private PersonUserService personUserService;
    @Autowired
    private SenderService senderService;
    private Logger log = LoggerFactory.getLogger(ChatController.class);

    //进入发送信息页面
    @RequestMapping("/send")
    public String toSend(Map<String,Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        PersonUser personUser = personUserService.queryByAccount(userDetails.getUsername());
        model.put("person",personUser);
        System.out.println("chat");
        return "/rabbitmq/sender";
    }

    //发送信息
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse send(Principal principal, String message){
        String account = principal.getName();
        System.out.println("send");
        return senderService.send(account,message);
    }
}
