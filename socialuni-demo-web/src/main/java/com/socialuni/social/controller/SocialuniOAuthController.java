package com.socialuni.social.controller;


import com.socialuni.social.sdk.logic.service.SocialuniOAuthService;
import com.socialuni.social.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.social.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
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