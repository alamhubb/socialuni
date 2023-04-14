package com.socialuni.social.im.config.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.security.Principal;


@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WebSocketUtil implements Principal {

    private String username;

    @Override
    public String getName() {
        return this.username;
    }

    public WebSocketUtil() {
    }

    public WebSocketUtil(String username) {
        this.username = username;
    }
}
