package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.api.SocialuniUserIdentityAPIImpl;
import com.socialuni.sdk.feignAPI.SocialuniUserIdentityAPI;
import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterUserIdentityController implements SocialuniUserIdentityAPI {
    @Resource
    private SocialuniUserIdentityAPIImpl centerUserIdentityAPIImpl;

    @Override
    public ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityAPIImpl.userIdentityAuthPreCheck(socialUseIdentityAuthQO);
    }

    @Override
    public ResultRO<String> userIdentityAuth(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityAPIImpl.userIdentityAuth(socialUseIdentityAuthQO);
    }
}
