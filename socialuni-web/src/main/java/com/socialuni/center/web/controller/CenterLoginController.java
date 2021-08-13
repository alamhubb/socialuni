package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniLoginAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.serive.CenterLoginService;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 14:14
 */
@RestController
public class CenterLoginController implements SocialuniLoginAPI {
    @Resource
    private CenterLoginService centerLoginService;

    @Override
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        SocialLoginRO<CenterMineUserDetailRO> socialUserDetailRO = centerLoginService.providerLogin(loginData);
        return new ResultRO<>(socialUserDetailRO);
    }
}
