package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mock")
@FeignClient(name = "mock", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniMockOAuthAPI {
    /*@PostMapping("mockOAuthUserInfo")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo();*/


    @PostMapping("mockOAuthUserPhoneNum")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum();
}