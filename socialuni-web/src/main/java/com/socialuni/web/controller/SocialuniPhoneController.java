package com.socialuni.web.controller;

import com.socialuni.sdk.logic.service.phone.SocialuniPhoneService;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("phone")
public class SocialuniPhoneController implements SocialuniPhoneAPI {
    @Resource
    SocialuniPhoneService centerPhoneService;

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return centerPhoneService.sendAuthCode(authCodeQO);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        return centerPhoneService.phoneLogin(socialPhoneNumQO);
    }
}
