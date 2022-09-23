package com.socialuni.web.controller;

import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mock")
public interface SocialuniMockOAuthAPI {
    /*@PostMapping("mockOAuthUserInfo")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo();*/


    @PostMapping("mockOAuthUserPhoneNum")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum();
}