package com.socialuni.demo.config.websocket;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.config.websocket.WebSocketUser;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        //websocket下就从req中拿token
        //获取token认证
        String token = SocialTokenUtil.getToken();
        //32为非法token，但是在这里是正常token，代表临时用户
        //如果为32位token则在这里可以直接使用
        if (SocialTokenUtil.isError(token) && (token == null || token.length() != 32)) {
            ServletServerHttpRequest req = (ServletServerHttpRequest) request;
            token = req.getServletRequest().getParameter("token");
        }
        UserDO user = SocialUserUtil.getUserByWebsocketToken(token);
        if (user != null) {
            return new WebSocketUser(user.getId().toString());
        } else {
            return new WebSocketUser(token);
        }
    }
}