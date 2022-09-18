package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.user.SocialuniUserIdentityAPI;
import com.socialuni.sdk.logic.service.user.SocialuniUserIdentityService;
import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniUserIdentityController implements SocialuniUserIdentityAPI {
    @Resource
    private SocialuniUserIdentityService centerUserIdentityAPIImpl;

    @Override
    public ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityAPIImpl.userIdentityAuthPreCheck(socialUseIdentityAuthQO);
    }

    @Override
    public ResultRO<String> userIdentityAuth(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityAPIImpl.userIdentityAuth(socialUseIdentityAuthQO);
    }
}
