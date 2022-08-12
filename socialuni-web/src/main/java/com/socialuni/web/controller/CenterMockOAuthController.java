package com.socialuni.web.controller;


import com.socialuni.sdk.serive.CenterMockService;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterMockOAuthController implements SocialuniMockOAuthAPI {
    @Resource
    CenterMockService centerMockService;

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