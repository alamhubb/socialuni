package com.socialuni.demo.socialuni;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.service.SocialLoginService;
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
    private SocialLoginService socialLoginService;

    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("providerLogin")
    public ResultRO<SocialLoginRO<SocialMineUserDetailRO>> providerLogin(@RequestBody @Valid SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<SocialMineUserDetailRO>> resultRO = socialLoginService.providerLogin(loginData);
        return resultRO;
    }
}
