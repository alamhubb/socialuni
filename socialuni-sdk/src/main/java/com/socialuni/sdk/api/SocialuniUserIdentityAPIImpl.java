package com.socialuni.sdk.api;

import com.socialuni.sdk.feignAPI.SocialuniUserIdentityAPI;
import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.sdk.serive.CenterUserIdentityService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniUserIdentityAPIImpl implements SocialuniUserIdentityAPI {
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
