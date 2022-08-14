package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniThirdUserAPI;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.serive.CenterThirdUserService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterThirdUserController implements SocialuniThirdUserAPI {
    @Resource
    CenterThirdUserService centerThirdUserService;

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> registryUser(SocialProviderLoginQO loginQO) {
        return centerThirdUserService.registryUser(loginQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> queryThirdUser() {
        return centerThirdUserService.queryThirdUser();
    }
}
