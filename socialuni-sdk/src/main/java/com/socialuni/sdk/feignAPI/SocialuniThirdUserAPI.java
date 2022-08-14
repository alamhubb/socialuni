package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("socialuni/thirdUser")
@FeignClient(name = "thirdUser", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniThirdUserAPI {
    @PostMapping("registryUser")
    ResultRO<SocialLoginRO<CenterMineUserDetailRO>> registryUser(@RequestBody @Valid SocialProviderLoginQO loginQO);

    @PostMapping("queryThirdUser")
    ResultRO<CenterMineUserDetailRO> queryThirdUser();
}