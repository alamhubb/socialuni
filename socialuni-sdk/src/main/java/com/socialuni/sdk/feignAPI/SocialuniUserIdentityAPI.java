package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("userIdentity")
public interface SocialuniUserIdentityAPI {
//    @PostMapping("idImgAuthPreCheck")
//    ResultRO<SocialUserIdentityAuthPreCheckRO> idImgAuthPreCheck(@RequestBody @Valid SocialUserIdImgCheckQO socialUserIdImgCheckQO);

    @PostMapping("userIdentityAuthPreCheck")
    ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);

    @PostMapping("userIdentityAuth")
    ResultRO<String> userIdentityAuth(@RequestBody @Valid SocialUserIdentityAuthQO socialUseIdentityAuthQO);
}