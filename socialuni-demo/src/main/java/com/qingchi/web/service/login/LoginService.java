package com.qingchi.web.service.login;

import com.qingchi.web.entity.ProviderLoginEntity;
import com.qingchi.web.model.RO.LoginRO;
import com.socialuni.api.feignAPI.insystem.SocialuniQingchiAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class LoginService {
    @Resource
    SocialuniQingchiAPI socialuniQingchiAPI;
    @Resource
    private ProviderLoginEntity providerLoginEntity;

    public ResultRO<LoginRO> providerLogin(SocialProviderLoginQO loginQO) {
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> centerResultRO = socialuniQingchiAPI.providerLogin(loginQO);

        LoginRO loginRO = providerLoginEntity.centerLogin(centerResultRO.getData());

        return new ResultRO<>(loginRO);
    }

    public ResultRO<LoginRO> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> centerResultRO = socialuniQingchiAPI.phoneLogin(socialPhoneNumQO);

        LoginRO loginRO = providerLoginEntity.centerLogin(centerResultRO.getData());

        return new ResultRO<>(loginRO);
    }
}
