package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniLoginAPI;
import com.socialuni.sdk.logic.service.login.SocialuniLoginService;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
