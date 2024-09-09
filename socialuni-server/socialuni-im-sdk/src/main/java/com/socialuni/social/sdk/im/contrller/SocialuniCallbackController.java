package com.socialuni.social.sdk.im.contrller;

import com.socialuni.social.im.api.feign.SocialuniCallbackAPI;
import com.socialuni.social.im.api.model.DTO.SocialuniNotifyDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("socialuni/callback")
public class SocialuniCallbackController implements SocialuniCallbackAPI {


    @Override
    public <T> void receiveNotify(SocialuniNotifyDTO<T> notifyDTO) {


    }
}
