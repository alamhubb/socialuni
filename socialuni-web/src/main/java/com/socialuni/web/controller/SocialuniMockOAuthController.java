package com.socialuni.web.controller;


import com.socialuni.sdk.logic.service.SocialuniMockService;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniMockOAuthController implements SocialuniMockOAuthAPI {
    @Resource
    SocialuniMockService centerMockService;

    /*@Override
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo() {
        ResultRO<SocialLoginRO<SocialOAuthUserRO>> resultRO = centerMockService.mockOAuthUserInfo();
        return resultRO;
    }*/

    @Override
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum() {
        return centerMockService.mockOAuthUserPhoneNum();
    }
}