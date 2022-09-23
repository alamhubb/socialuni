package com.socialuni.sdk.feignAPI.openData;

import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.common.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("socialuni/thirdUser")
@FeignClient(name = "thirdUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "开放数据/开放用户数据")
public interface SocialuniThirdUserAPI {
    @PostMapping("registryUser")
    @Operation(summary = "注册用户，由三方应用服务端发起，携带用户信息和秘钥")
    ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> registryUser(@RequestBody @Valid SocialProviderLoginQO loginQO);

    @GetMapping("queryThirdUser")
    @Operation(summary = "查询用户信息，由三方应用服务端发起，携带用户token")
    ResultRO<SocialuniMineUserDetailRO> queryThirdUser();
}