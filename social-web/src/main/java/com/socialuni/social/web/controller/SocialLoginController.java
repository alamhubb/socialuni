package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialLoginService;
import com.socialuni.social.sdk.url.user.SocialuniLoginUrl;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialLoginController implements SocialuniLoginUrl {
    @Resource
    private SocialLoginService socialLoginService;

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    public ResultRO<SocialLoginRO<SocialMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        return socialLoginService.providerLogin(loginData);
    }

    public ResultRO<SocialLoginRO<SocialMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        return socialLoginService.phoneLogin(socialPhoneNumQO);
    }
}
