package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.api.user.SocialuniUserIdentityAPI;
import com.socialuni.social.sdk.logic.service.user.SocialuniUserIdentityService;
import com.socialuni.social.user.sdk.model.QO.SocialUserIdentityAuthQO;
import com.socialuni.social.user.sdk.model.RO.SocialUserIdentityAuthPreCheckRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/userIdentity")
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
