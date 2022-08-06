package com.socialuni.center.web.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.web.sdk.utils.JsonUtil;
import org.springframework.web.socket.TextMessage;

public class WebsocketUtil {
    public <T> TextMessage toMessage(T object) {
        try {
            return new TextMessage(JsonUtil.objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
