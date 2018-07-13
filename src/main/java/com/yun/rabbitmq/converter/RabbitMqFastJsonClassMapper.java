package com.yun.rabbitmq.converter;

import org.springframework.amqp.support.converter.DefaultClassMapper;

/**
 * fastjson 转换映射
 */
public class RabbitMqFastJsonClassMapper extends DefaultClassMapper {
    /**
     * 构造函数初始化信任所有pakcage
     */
    public RabbitMqFastJsonClassMapper() {
        super();
        setTrustedPackages("*");//信任全部的package
    }
}
