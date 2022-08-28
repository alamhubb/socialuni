package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("socialuni/userIdentity")
@FeignClient(name = "userIdentity", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniUserIdentityAPI {
//    @PostMapping("idImgAuthPreCheck")
//    ResultRO<SocialUserIdentityAuthPreCheckRO> idImgAuthPreCheck(@RequestBody @Valid SocialUserIdImgCheckQO socialUserIdImgCheckQO);

    @PostMapping("userIdentityAuthPreCheck")
    ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);

    @PostMapping("userIdentityAuth")
    ResultRO<String> userIdentityAuth(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);
}