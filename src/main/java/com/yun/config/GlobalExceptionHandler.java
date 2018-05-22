package com.yun.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理 zyj
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        Map map =new HashMap();
        map.put("code","477");
        map.put("message",e.getMessage());
        map.put("data",e.getClass());
        return map;
    }
}
