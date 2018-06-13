package com.yun.websocket.domain;

import lombok.Data;

@Data
public class WSResponse {
    private String responseMessage;
    public WSResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
}
