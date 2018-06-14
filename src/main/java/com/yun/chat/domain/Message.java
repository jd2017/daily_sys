package com.yun.chat.domain;

import lombok.Data;

@Data
public class Message {
    private String sendTo;
    private String message;
}
