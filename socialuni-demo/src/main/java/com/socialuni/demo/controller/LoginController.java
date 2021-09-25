package com.socialuni.demo.controller;

import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.service.LoginService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("login")
public class LoginController {
    @Resource
    private LoginService loginService;

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("providerLogin")
    public ResultRO<SocialLoginRO<MineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<MineUserDetailRO>> resultRO = loginService.providerLogin(loginData);
        return resultRO;
    }

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("socialuniPhoneLogin")
    public ResultRO<SocialLoginRO<MineUserDetailRO>> socialuniPhoneLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<MineUserDetailRO>> resultRO = loginService.socialuniPhoneLogin(loginData);
        return resultRO;
    }
}
