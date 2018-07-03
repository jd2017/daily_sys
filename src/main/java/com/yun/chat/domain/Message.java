package com.yun.chat.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "chatmessage")
public class Message {
    @Id
    private String id;
    private String sender;//发送消息的人员账户
    private String senderName;//发送消息的人员姓名
    private String sendTo;//接受者账户
    private String message;//消息
    private String sendTime;//消息发送时间
    private int isRead = 0;//是否已读 1：已读  0：未读
}
