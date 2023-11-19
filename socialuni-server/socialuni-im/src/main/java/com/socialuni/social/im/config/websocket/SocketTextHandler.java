package com.socialuni.social.im.config.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        String payload = message.getPayload();

        System.out.println("收到了消息");
        System.out.println(payload);
//        session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
    }

}