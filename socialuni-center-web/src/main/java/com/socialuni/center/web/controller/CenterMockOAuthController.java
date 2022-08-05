package com.socialuni.center.web.controller;


import com.socialuni.center.web.feignAPI.SocialuniMockOAuthAPI;
import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.serive.CenterMockService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.QO.user.MockOAuthUserInfoQO;
import com.socialuni.center.web.model.RO.SocialOAuthUserRO;
import com.socialuni.center.web.model.RO.user.login.SocialLoginRO;
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