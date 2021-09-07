package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniLoginAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.serive.CenterLoginService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * 目前认为用不到这个类,社交联盟不提供登录功能，只提供授权功能
 * @date 2019-02-17 14:14
 */
@RestController
public class CenterLoginController implements SocialuniLoginAPI {
    @Resource
    private CenterLoginService centerLoginService;

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        return centerLoginService.providerLogin(loginData);
    }
}
