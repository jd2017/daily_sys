package com.yun.websocket.domain;

import lombok.Data;

@Data
public class WSResponse {
    private String responseMessage;
    private String fromAccount ;
    private String fromName;
    public WSResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public WSResponse(String responseMessage,String fromAccount,String fromName){
        this.responseMessage = responseMessage;
        this.fromAccount=fromAccount;
        this.fromName=fromName;
    }
}
