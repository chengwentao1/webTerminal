package com.webterminal.backendterminal.config;


import com.webterminal.backendterminal.interceptor.WebSocketInterceptor;
import com.webterminal.backendterminal.websocket.WebSSHWebSocketHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;



/**
* @Description: websocket配置
* @Author: NoCortY
* @Date: 2020/3/8
*/
@Configuration
@EnableWebSocket
public class WebSSHWebSocketConfig implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        WebSSHWebSocketHandler webSSHWebSocketHandler=new WebSSHWebSocketHandler();
        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry.addHandler(webSSHWebSocketHandler, "/webssh")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
