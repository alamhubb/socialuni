package com.socialuni.social.sdk.controller;

import com.socialuni.social.app.logic.service.SocialuniDetailLoginService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.tance.dev.api.SocialuniNoUseFeignAspect;
import com.socialuni.social.user.sdk.api.user.SocialuniLoginAPI;
import com.socialuni.social.user.sdk.model.QO.*;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
 */
@RestController
@RequestMapping("socialuni/login")
@SocialuniNoUseFeignAspect
public class SocialuniLoginController implements SocialuniLoginAPI {
    @Resource
    private SocialuniDetailLoginService centerLoginService;
    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @Override
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        return centerLoginService.providerLogin(loginData);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserShowRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> passwordLogin(SocialPhoneNumPasswordQO socialPhoneNumQO) {
        return centerLoginService.passwordLogin(socialPhoneNumQO);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> phonePasswordLogin(SocialPhoneAuthCodePasswordQO socialPhoneNumQO) {
        return centerLoginService.phonePasswordLogin(socialPhoneNumQO);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> deviceUidLogin(SocialuniDeviceUidLoginQO socialuniDeviceUidLoginQO) {
        return centerLoginService.deviceUidLogin(socialuniDeviceUidLoginQO);
    }
}
