package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniLoginAPI;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.serive.CenterLoginService;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
 */
@RestController
public class CenterLoginController implements SocialuniLoginAPI<CenterMineUserDetailRO> {
    @Resource
    private CenterLoginService centerLoginService;

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        throw new SocialBusinessException("不支持三方应用使用非清池渠道直接登录");
    }

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        return centerLoginService.socialuniPhoneLogin(loginData);
    }

    @PostMapping("phoneLogin")
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }
}
