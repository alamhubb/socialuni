package com.socialuni.social.controller;


import com.socialuni.social.sdk.logic.service.SocialuniMockService;
import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
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