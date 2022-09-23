package com.socialuni.social.sdk.feignAPI.user;

import com.socialuni.social.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.social.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.common.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("socialuni/userIdentity")
@Tag(name = "用户模块/用户认证模块")
@FeignClient(name = "userIdentity", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniUserIdentityAPI {
//    @PostMapping("idImgAuthPreCheck")
//    ResultRO<SocialUserIdentityAuthPreCheckRO> idImgAuthPreCheck(@RequestBody @Valid SocialUserIdImgCheckQO socialUserIdImgCheckQO);

    @PostMapping("userIdentityAuthPreCheck")
    @Operation(summary = "用户实名认证预校验")
    ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);

    @PostMapping("userIdentityAuth")
    @Operation(summary = "用户实名认证")
    ResultRO<String> userIdentityAuth(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);
}