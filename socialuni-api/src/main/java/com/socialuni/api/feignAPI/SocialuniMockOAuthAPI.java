package com.socialuni.api.feignAPI;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.MockQO;
import com.socialuni.social.model.model.QO.user.MockOAuthUserInfoQO;
import com.socialuni.social.model.model.QO.user.OAuthUserInfoQO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mock")
@FeignClient(name = "mock", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniMockOAuthAPI {
    /*@PostMapping("mockOAuthUserInfo")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo();*/


    @PostMapping("mockOAuthUserPhoneNum")
    ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum();
}