package com.socialuni.social.sdk.im.config.websocket;

import com.socialuni.social.common.api.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SocketTextHandler extends TextWebSocketHandler {
    public static final ConcurrentHashMap<String, WebSocketSession> onlineUsersSessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Principal user = session.getPrincipal();
        if (user != null && StringUtils.isNotEmpty(user.getName())) {
            log.info("登录成功:{}", user.getName());
            String userId = user.getName();
            onlineUsersSessionMap.put(userId, session);
        } else {

            onlineUsersSessionMap.put(UUIDUtil.getUUID(), session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        log.debug("连接已关闭：" + status);
        onlineUsersSessionMap.remove(session.getPrincipal().getName());
        session.close();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        String payload = message.getPayload();

        String curName = session.getPrincipal().getName();

        System.out.println(curName);
        System.out.println(onlineUsersSessionMap.values().size());
        Integer index = 0;
        for (WebSocketSession s : onlineUsersSessionMap.values()) {
            if (!s.getPrincipal().getName().equals(curName)) {
                if (StringUtils.isNotBlank(payload) && !payload.equals("ping")) {
                    index++;
                    s.sendMessage(message);
                    System.out.println(index);
                }
            }
        }
        System.out.println("收到了消息");
        System.out.println(payload);
//        session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
    }


}