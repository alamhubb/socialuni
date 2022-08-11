package com.socialuni.sdk.controller;


import com.socialuni.sdk.feignAPI.SocialuniOAuthAPI;
import com.socialuni.sdk.serive.CenterOAuthService;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterOAuthController implements SocialuniOAuthAPI {

    @Resource
    CenterOAuthService centerOAuthService;

    @Override
    public ResultRO<OAuthGetUserPhoneNumRO> oAuthGetUserPhoneNum() {
        return centerOAuthService.getUserPhoneNum();
    }

    @Override
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfoAndPhoneNum(OAuthUserInfoQO authVO) {
        return centerOAuthService.oAuthUserInfoAndPhoneNum(authVO);
    }
}