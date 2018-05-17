package com.yun.daily.personUser.controller;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.personUser.service.PersonUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by itw_zhuyj on 2018/4/2.
 */


@Controller
@Api(value = "PersonUserController",description = "客户端用户信息")
@RequestMapping(value = "/api/v1/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonUserController {
    @Autowired
    private PersonUserService personUserService;
    private Logger log = LoggerFactory.getLogger(PersonUserController.class);

    @ApiOperation(value = "查询用户",notes = "根据用户id查询用户",httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "personId",value = "用户id",required = true,dataType = "String",paramType = "path")
    })
    @RequestMapping("/queryUserById/{personId}")
    public  String queryUserById(Map<String,Object> model,@PathVariable String personId){
        model.put("users",personUserService.queryUserById(personId));
        return "index";
    }

    @RequestMapping("/insertUser/{personId}")
    public  String insertUser(Map<String,Object> model,PersonUser personUser){
        model.put("user",personUserService.insert(personUser));
        return "index";
    }

}
