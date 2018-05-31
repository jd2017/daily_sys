package com.yun.daily.system.controller;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import com.yun.daily.system.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

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
    @ResponseBody
    public  ResponseEntity<?> login(Map<String,Object> model, PersonUser personUser) throws AuthenticationException{
        logger.info("AuthController.login--Parameter:"+personUser);
        final String token = authService.login(personUser.getAccount(),personUser.getPassword());
        logger.info("AuthController.login--result:"+token);
        ResponseEntity<?> result = ResponseEntity.ok(token);
        logger.info("AuthController.login--ResponseEntity.ok(token):"+result);
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
        encryptPassword(personUser);
        model.put("user",personUserService.insert(personUser));
        return "/login";
    }

    /**
     * 注册客户端用户
     * @return
     */
    @RequestMapping(value = "/validateAccount")
    @ResponseBody
    public boolean validateAccount(String account){
        PersonUser personUser = personUserService.queryByAccount(account);
        return personUser==null;
    }


    /**
     * 加密密码
     */
    private void encryptPassword(PersonUser personUser){
        String password = personUser.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        personUser.setPassword(password);
    }
}
