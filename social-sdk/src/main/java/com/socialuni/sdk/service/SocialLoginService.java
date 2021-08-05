package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.login.SocialPhoneLoginDomain;
import com.socialuni.sdk.domain.login.SocialProviderLoginDomain;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialLoginService {
    @Resource
    SocialProviderLoginDomain socialProviderLoginDomain;
    @Resource
    SocialPhoneLoginDomain socialPhoneLoginDomain;


    public ResultRO<SocialLoginRO<SocialMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);
        return new ResultRO<>(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialPhoneLoginDomain.phoneLogin(socialPhoneNumQO);
        return new ResultRO<>(socialLoginRO);
    }
}