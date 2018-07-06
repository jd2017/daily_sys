package com.yun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //启用STOMP消息。通过该注解开启使用stomp协议来传输基于代理(message broker)的消息，这时控制器支持使用@MessageMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//注册stomp协议的节点（endpoint），并映射的指定的URL
        /*浏览器和应用服务器由于版本各种问题，会对websocket的支持不足，这里SockJS起到了关键作用。
        SockJS是websocket技术的模拟，表面上它尽可能应对websocket api,底层却非常智能。如果websocket不可用的话，它会选择其他通信方式，
        SockJS会优先选用websocket   withSockJS()为"/endpoint*"开启SockJS功能 前端会用到sockjs.min.js*/
        registry.addEndpoint("/endpointWS").withSockJS();//注册一个stomp的endpoint，并指定使用SockJS协议  广播式
        registry.addEndpoint("/endpointChat").withSockJS();//注册一个名为endpointChat的endpoint 点对点
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理（Message Broker）
//       registry.enableSimpleBroker("/topic");//广播式应配置一个/topic消息代理
//       registry.enableSimpleBroker("/queue","/topic");//点对点式应增加一个/queue消息代理

       //启用STOMP代理中继（broker relay）功能,并将其目的地前缀设置为"/queue"和"/topic"
       registry.enableStompBrokerRelay("/queue","/topic")
               .setRelayHost("39.106.13.197")
               .setRelayPort(61613)
               .setClientLogin("guest")
               .setClientPasscode("guest")
               .setSystemLogin("guest")
               .setSystemPasscode("guest");

       //将应用的前缀设置为app,所有目的地以"/app" 打头的消息都将会路由到带有@MessageMapping注解的方法中，而不会发布到代理队列或主题中
//       registry.setApplicationDestinationPrefixes("/app");
    }
}
