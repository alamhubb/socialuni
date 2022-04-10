package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniUserIdentityAPI;
import com.socialuni.center.web.serive.CenterUserIdentityService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.social.model.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterUserIdentityController implements SocialuniUserIdentityAPI {
    @Resource
    private CenterUserIdentityService centerUserIdentityService;

    @Override
    public ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityService.userIdentityAuthPreCheck(socialUseIdentityAuthQO);
    }

    @Override
    public ResultRO<String> userIdentityAuth(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityService.userIdentityAuth(socialUseIdentityAuthQO);
    }
}
