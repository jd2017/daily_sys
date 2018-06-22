package com.yun.common;

import lombok.Data;

/**
 * 统一返回对象
 * @author zyj 2018-6-22
 */
@Data
public class JsonResponse {
    private String code;
    private String message;
    private Object data;

    public JsonResponse(){
        this.code=ResponseCode.NONE;
        this.data=null;
    }

    public void  addMessage(String message){setMessage(getMessage()+"; "+message);}
}
