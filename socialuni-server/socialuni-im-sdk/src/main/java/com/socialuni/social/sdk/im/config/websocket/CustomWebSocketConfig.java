package com.socialuni.social.sdk.im.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class CustomWebSocketConfig implements WebSocketConfigurer {

    @Resource
    private WebsocketServer websocketServer;
    @Resource
    private SocketTextHandler socketTextHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        /**
         * 注释websocket
         */
        registry
                .addHandler(socketTextHandler, "/webrtc")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .addHandler(websocketServer, "/webSocket/message")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .setAllowedOrigins("*");
    }
}