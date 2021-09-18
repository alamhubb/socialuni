package com.socialuni.center.web.controller;


import com.socialuni.api.feignAPI.SocialuniOAuthAPI;
import com.socialuni.center.web.serive.CenterOAuthService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.OAuthUserPhoneNumRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterOAuthController implements SocialuniOAuthAPI {

    @Resource
    CenterOAuthService centerOAuthService;

    @Override
    public ResultRO<OAuthUserPhoneNumRO> getOAuthUserPhoneNum() {
        return centerOAuthService.getOAuthUserPhoneNum();
    }
}