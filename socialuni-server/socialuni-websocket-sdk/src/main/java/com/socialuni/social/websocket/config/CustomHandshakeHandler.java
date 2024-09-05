package com.socialuni.social.websocket.config;

import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.http.server.ServerHttpRequest;
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
        String token = SocialTokenFacade.getToken();

        System.out.println("触发了");
        System.out.println(token);
        if (SocialTokenFacade.isError(token)) {
            return null;
        }
        SocialuniUserDo user = SocialuniUserUtil.getUserByToken(token);
        if (user != null) {
            String userUid = String.valueOf(SocialuniUnionIdFacede.getUuidByUnionIdNotNull(user.getUserId()));
            //使用string，如果int to string 则还需要判断转换
            return new WebSocketUser(userUid);
//            return new WebSocketUser(user.getUnionId().toString());
        } else {
            return new WebSocketUser(token);
        }
    }
}