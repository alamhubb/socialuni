package com.socialuni.social.demo.controller;

import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mock")
public interface SocialuniMockOAuthAPI {
    /*@PostMapping("mockOAuthUserInfo")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo();*/


    @PostMapping("mockOAuthUserPhoneNum")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum();
}