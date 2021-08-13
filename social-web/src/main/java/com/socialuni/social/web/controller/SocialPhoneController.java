package com.socialuni.social.web.controller;

import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.sdk.service.phone.SocialPhoneService;
import com.socialuni.social.sdk.url.user.SocialuniPhoneUrl;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialPhoneController implements SocialuniPhoneUrl {

    @Resource
    SocialPhoneService socialPhoneService;

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return socialPhoneService.sendAuthCode(authCodeQO);
    }

    @Override
    public ResultRO<SocialMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO phoneNumQO) {
        return socialPhoneService.bindPhoneNum(phoneNumQO);
    }

    @Override
    public ResultRO<SocialMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO socialBindWxPhoneNumQO) {
        return socialPhoneService.bindWxPhoneNum(socialBindWxPhoneNumQO);
    }
}
