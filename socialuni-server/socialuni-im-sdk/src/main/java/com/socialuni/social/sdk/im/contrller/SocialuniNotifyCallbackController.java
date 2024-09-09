package com.socialuni.social.sdk.im.contrller;

import com.socialuni.social.im.api.feign.SocialuniCallbackAPI;
import com.socialuni.social.common.api.model.SocialuniNotifyRO;
import com.socialuni.social.sdk.im.config.websocket.WebsocketServer;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.dao.repository.DevAccountRepository;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("socialuni/notifyCallback")
public class SocialuniNotifyCallbackController implements SocialuniCallbackAPI {

    @Override
    public void receiveNotify(URI host, SocialuniNotifyRO socialuniNotifyRO) {
        WebsocketServer.sendToGroupUsers(socialuniNotifyRO);
    }
}
