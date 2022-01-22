package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniPhoneAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.serive.CenterPhoneService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("phone")
public class CenterPhoneController implements SocialuniPhoneAPI {
    @Resource
    CenterPhoneService centerPhoneService;

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return centerPhoneService.sendAuthCode(authCodeQO);
    }

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        return centerPhoneService.phoneLogin(socialPhoneNumQO);
    }
}
