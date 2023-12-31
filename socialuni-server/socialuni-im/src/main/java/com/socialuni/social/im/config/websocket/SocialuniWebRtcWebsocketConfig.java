package com.socialuni.social.im.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;
import java.util.logging.SocketHandler;

@Configuration
@EnableWebSocket
public class SocialuniWebRtcWebsocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocialuniWebRtcHandler(), "/socket")
                .setAllowedOrigins("*");
    }
}