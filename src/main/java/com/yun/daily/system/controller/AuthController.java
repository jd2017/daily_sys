package com.yun.daily.system.controller;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import com.yun.daily.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by itw_zhuyj on 2018/4/2.
 */


@Controller
public class AuthController {
    @Autowired
    private PersonUserService personUserService;


    @Autowired
    private AuthService authService;

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

    /**
     * 登录
     * @param model
     * @param personUser
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public  ResponseEntity<?> login(Map<String,Object> model, PersonUser personUser) throws AuthenticationException{
        final String token = authService.login(personUser.getAccount(),personUser.getPassword());
        ResponseEntity<?> result = ResponseEntity.ok(token);
        return result;
    }

    /**
     * 注册客户端用户
     * @param model
     * @param personUser
     * @return
     */
    @RequestMapping(value = "/register",method= RequestMethod.POST)
    public  String regester(Map<String,Object> model, PersonUser personUser){
        model.put("user",personUserService.insert(personUser));
        return "/login";
    }
}
