package com.qingchi.web.controller.socialuni;

import com.qingchi.web.model.RO.LoginRO;
import com.qingchi.web.service.login.LoginService;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.api.model.ResultRO;
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
    public ResultRO<LoginRO> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        ResultRO<LoginRO> resultRO = loginService.providerLogin(loginData);
        return resultRO;
    }
    @PostMapping("phoneLogin")
    public ResultRO<LoginRO> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<LoginRO> resultRO = loginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }
}
