package com.yun.rabbitmq.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyMessage implements Serializable{
    private String sender;
    private String msg;
}
