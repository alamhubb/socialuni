package com.socialuni.center.web.controller;


import com.socialuni.api.feignAPI.SocialuniMockOAuthAPI;
import com.socialuni.api.feignAPI.SocialuniOAuthAPI;
import com.socialuni.center.web.serive.CenterMockService;
import com.socialuni.center.web.serive.OAuthService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.OAuthUserPhoneNumRO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterOAuthController implements SocialuniOAuthAPI {

    @Resource
    OAuthService oAuthService;

    @Override
    public ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum() {
        return oAuthService.getOAuthUserPhoneNum();
    }
}