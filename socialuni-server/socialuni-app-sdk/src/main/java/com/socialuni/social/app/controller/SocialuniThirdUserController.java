package com.socialuni.social.app.controller;

import com.socialuni.social.app.logic.service.SocialuniThirdUserService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.user.sdk.api.SocialuniThirdUserAPI;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/thirdUser")
public class SocialuniThirdUserController implements SocialuniThirdUserAPI {
    @Resource
    SocialuniThirdUserService centerThirdUserService;

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> registryUser(SocialProviderLoginQO loginQO) {
        return centerThirdUserService.registryUser(loginQO);
    }

    @Override
    public ResultRO<SocialuniUserShowRO> queryThirdUser() {
        return centerThirdUserService.queryThirdUser();
    }
}

