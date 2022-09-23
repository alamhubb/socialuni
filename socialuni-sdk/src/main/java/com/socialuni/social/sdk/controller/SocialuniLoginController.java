package com.socialuni.social.sdk.controller;

import com.socialuni.social.sdk.feignAPI.user.SocialuniLoginAPI;
import com.socialuni.social.sdk.logic.service.login.SocialuniLoginService;
import com.socialuni.social.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
 */
@RestController
public class SocialuniLoginController implements SocialuniLoginAPI {
    @Resource
    private SocialuniLoginService centerLoginService;

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @Override
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        return centerLoginService.providerLogin(loginData);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }
}
