package com.socialuni.social.sdk.im.config.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.security.Principal;


@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WebSocketUser implements Principal {

    private String username;

    @Override
    public String getName() {
        return this.username;
    }

    public WebSocketUser() {
    }

    public WebSocketUser(String username) {
        this.username = username;
    }
}
