package com.socialuni.social.sdk.im.logic.domain;

import com.socialuni.social.im.api.feign.SocialuniCallbackAPI;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.config.websocket.WebsocketServer;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.factory.SocialuniNotifyROFactory;
import com.socialuni.social.common.api.model.SocialuniNotifyRO;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.dao.repository.DevAccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@Service
public class SocialuniWebcoketSendDomain {

    //负责给所有用户推送消息，但不负责存库。

    //一个是notify
    //一个是

    @Resource
    SocialuniCallbackAPI socialuniCallbackAPI;

    @Resource
    DevAccountRepository devAccountRepository;

    public void sengGroupMsg(SocialuniNotifyRO socialuniNotifyRO) {

        if (SocialuniDevConfig.serverIsCenter()) {
            WebsocketServer.sendToGroupUsers(socialuniNotifyRO);
        }
        List<DevAccountDo> list = devAccountRepository.findAll();
        for (DevAccountDo devAccountDo : list) {
            String apiUrl = devAccountDo.getApiUrl();
            String apiKey = devAccountDo.getApiSecretKey();
            if (StringUtils.isNotBlank(apiUrl) && StringUtils.isNotBlank(apiKey)) {
                URI uri = URI.create(apiUrl);
                socialuniCallbackAPI.receiveNotify(uri, socialuniNotifyRO);
            }
        }
    }

}
