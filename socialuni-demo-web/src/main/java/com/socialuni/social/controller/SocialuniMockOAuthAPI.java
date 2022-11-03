package com.socialuni.social.controller;

import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mock")
public interface SocialuniMockOAuthAPI {
    /*@PostMapping("mockOAuthUserInfo")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo();*/


    @PostMapping("mockOAuthUserPhoneNum")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum();
}