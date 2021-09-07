package com.qingchi.web.service.login;

import com.qingchi.web.model.RO.LoginRO;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class UserLoginService {
    @Resource
    LoginService loginService;

    //旧版代码兼容
    private void oldVersionCompatibility(SocialProviderLoginQO loginQO) {
        //Provider = 'weixin' | 'qq'
        String provider = loginQO.getProvider();
        //手机登陆的时候为ProviderType.phone
        if (StringUtils.isEmpty(provider)) {
            provider = loginQO.getLoginType();
            loginQO.setProvider(provider);
        }
        String platform = loginQO.getPlatform();
        if (PlatformType.android.equals(platform)) {
            loginQO.setPlatform(PlatformType.app);
        }
    }

    //因旧版本原因合在了一起，新版本要拆开
    @Transactional
    public ResultRO<LoginRO> providerLogin(SocialProviderLoginQO loginData) {
        this.oldVersionCompatibility(loginData);
        return loginService.providerLogin(loginData);
    }
}
