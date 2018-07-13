package com.yun.rabbitmq.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MyMessage implements Serializable{
    @ApiModelProperty(value = "发送者账号")
    private String sender;
    @ApiModelProperty(value = "消息")
    private String msg;
}
