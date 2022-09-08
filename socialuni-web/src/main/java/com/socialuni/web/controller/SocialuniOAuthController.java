package com.socialuni.web.controller;


import com.socialuni.sdk.logic.service.SocialuniOAuthService;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniOAuthController implements SocialuniOAuthAPI {

    @Resource
    SocialuniOAuthService centerOAuthService;

    @Override
    public ResultRO<OAuthGetUserPhoneNumRO> oAuthGetUserPhoneNum() {
        return centerOAuthService.getUserPhoneNum();
    }

    @Override
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfoAndPhoneNum(OAuthUserInfoQO authVO) {
        return centerOAuthService.oAuthUserInfoAndPhoneNum(authVO);
    }
}