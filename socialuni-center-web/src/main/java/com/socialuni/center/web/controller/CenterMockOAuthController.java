package com.socialuni.center.web.controller;


import com.socialuni.api.feignAPI.SocialuniMockOAuthAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.serive.CenterMockService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.MockOAuthUserInfoQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterMockOAuthController implements SocialuniMockOAuthAPI {
    @Resource
    CenterMockService centerMockService;

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> mockOAuthUserInfo(MockOAuthUserInfoQO authVO) {
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> resultRO = centerMockService.mockOAuthUserInfo();
        return resultRO;
    }

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> mockOAuthUserPhoneNum(MockOAuthUserInfoQO authVO) {
        return centerMockService.mockOAuthUserPhoneNum();
    }
}