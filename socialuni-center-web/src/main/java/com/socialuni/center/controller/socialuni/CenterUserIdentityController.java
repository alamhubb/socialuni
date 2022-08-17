package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.sdk.serive.CenterUserIdentityService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CenterUserIdentityController {
    @Resource
    private CenterUserIdentityService centerUserIdentityAPIImpl;

    @PostMapping("userIdentityAuthPreCheck")
    public ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityAPIImpl.userIdentityAuthPreCheck(socialUseIdentityAuthQO);
    }

    @PostMapping("userIdentityAuth")
    public ResultRO<String> userIdentityAuth(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return centerUserIdentityAPIImpl.userIdentityAuth(socialUseIdentityAuthQO);
    }
}
