package com.yun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker //通过该注解开启使用stomp协议来传输基于代理(message broker)的消息，这时控制器支持使用@MessageMapping
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//注册stomp协议的节点（endpoint），并映射的指定的URL
        registry.addEndpoint("/endpointWS").withSockJS();//注册一个stomp的endpoint，并指定使用SockJS协议
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理（Message Broker）
       registry.enableSimpleBroker("/topic");//广播室颖配置一个/topic消息代理
    }
}
