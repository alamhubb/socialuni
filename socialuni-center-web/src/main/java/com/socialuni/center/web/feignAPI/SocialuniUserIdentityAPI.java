package com.socialuni.center.web.feignAPI;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.center.web.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("userIdentity")
@FeignClient(name = "userIdentity", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniUserIdentityAPI {
//    @PostMapping("idImgAuthPreCheck")
//    ResultRO<SocialUserIdentityAuthPreCheckRO> idImgAuthPreCheck(@RequestBody @Valid SocialUserIdImgCheckQO socialUserIdImgCheckQO);

    @PostMapping("userIdentityAuthPreCheck")
    ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);

    @PostMapping("userIdentityAuth")
    ResultRO<String> userIdentityAuth(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);
}