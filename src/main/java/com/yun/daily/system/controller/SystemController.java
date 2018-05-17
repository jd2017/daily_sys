package com.yun.daily.system.controller;

import com.yun.daily.personUser.domain.PersonUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by itw_zhuyj on 2018/4/2.
 */


@Controller
public class SystemController {
   /* @Autowired
    private UserService userService;*/
    @RequestMapping(value = {"/login","/"})
    public  String sigin(Map<String,Object> model){
        return "login";
    }

    @RequestMapping("/regester")
    public  String regester(Map<String,Object> model){
        return "regester";
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

}
