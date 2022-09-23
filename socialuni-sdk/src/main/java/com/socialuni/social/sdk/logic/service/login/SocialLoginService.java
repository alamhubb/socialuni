package com.socialuni.social.sdk.logic.service.login;

import com.socialuni.social.sdk.logic.domain.login.SocialPhoneLoginDomain;
import com.socialuni.social.sdk.logic.domain.login.SocialProviderLoginDomain;
import com.socialuni.social.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialLoginService {
    @Resource
    SocialProviderLoginDomain socialProviderLoginDomain;
    @Resource
    SocialPhoneLoginDomain socialPhoneLoginDomain;

    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);
        return new ResultRO<>(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = socialPhoneLoginDomain.phoneLogin(socialPhoneNumQO);
        return new ResultRO<>(socialLoginRO);
    }
}