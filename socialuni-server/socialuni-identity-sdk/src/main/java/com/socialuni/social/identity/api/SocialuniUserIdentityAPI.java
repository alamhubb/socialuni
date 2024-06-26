package com.socialuni.social.identity.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.SocialUserIdentityAuthQO;
import com.socialuni.social.user.sdk.model.RO.SocialUserIdentityAuthPreCheckRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


//@RequestMapping("socialuni/userIdentity")
@Tag(name = "用户模块/用户认证模块")
@FeignClient(name = "userIdentity", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/userIdentity")
public interface SocialuniUserIdentityAPI {
//    @PostMapping("idImgAuthPreCheck")
//    ResultRO<SocialUserIdentityAuthPreCheckRO> idImgAuthPreCheck(@RequestBody @Valid SocialUserIdImgCheckQO socialUserIdImgCheckQO);

    @GetMapping("getMineUserIdentityStatus")
    @Operation(summary = "获取用户实名认证状态")
    ResultRO<Boolean> getMineUserIdentityStatus();

    @PostMapping("userIdentityAuthPreCheck")
    @Operation(summary = "用户实名认证预校验")
    ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);

    @PostMapping("userIdentityAuth")
    @Operation(summary = "用户实名认证")
    ResultRO<String> userIdentityAuth(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);
}