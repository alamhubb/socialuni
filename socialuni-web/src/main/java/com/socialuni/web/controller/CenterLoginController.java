package com.socialuni.web.controller;

import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.serive.CenterLoginService;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialBusinessException;
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
@RequestMapping("login")
public class CenterLoginController{
    @Resource
    private CenterLoginService centerLoginService;
    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("providerLogin")
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        throw new SocialBusinessException("不支持三方应用使用非清池渠道直接登录");
    }


    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("socialuniPhoneLogin")
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> socialuniPhoneLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        return centerLoginService.socialuniPhoneLogin(loginData);
    }

    @PostMapping("phoneLogin")
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }
}
