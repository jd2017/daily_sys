package com.yun.daily.system.controller;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by itw_zhuyj on 2018/4/2.
 */


@Controller
public class SystemController {
    @Resource
    private PersonUserService personUserService;
    @RequestMapping(value = {"/login","/"})
    public  String sigin(Map<String,Object> model){
        return "login";
    }

    @RequestMapping("/register")
    public  String register(Map<String,Object> model){
        return "register";
    }

    @RequestMapping("/index")
    public  String index(Map<String,Object> model){
        return "index";
    }

    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public  String login(Map<String,Object> model, PersonUser personUser){
        System.out.println(1311);
        return "index";
    }
    @RequestMapping(value = "/register",method= RequestMethod.POST)
    public  String regester(Map<String,Object> model, PersonUser personUser){
        model.put("user",personUserService.insert(personUser));
        return "login";
    }
}
