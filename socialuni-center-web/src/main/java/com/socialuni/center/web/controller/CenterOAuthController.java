package com.socialuni.center.web.controller;


import com.socialuni.api.feignAPI.SocialuniOAuthAPI;
import com.socialuni.center.web.serive.CenterOAuthService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.OAuthUserInfoQO;
import com.socialuni.social.model.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
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
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> oAuthUserInfoAndPhoneNum( OAuthUserInfoQO authVO) {
        return centerOAuthService.oAuthUserInfoAndPhoneNum(authVO);
    }
}