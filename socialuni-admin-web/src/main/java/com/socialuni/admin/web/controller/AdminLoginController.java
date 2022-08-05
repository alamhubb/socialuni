package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.service.AdminLoginService;
import com.socialuni.center.web.model.QO.dev.DevAccountQueryQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
@Slf4j
public class AdminLoginController {
    @Resource
    AdminLoginService adminLoginService;

    @PostMapping("phoneLogin")
    public ResultRO<SocialLoginRO<DevAccountRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<DevAccountRO>> resultRO = adminLoginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }

    //秘钥登录
    @PostMapping("secretKeyLogin")
    public ResultRO<SocialLoginRO<DevAccountRO>> secretKeyLogin(@RequestBody @Valid DevAccountQueryQO devAccountQueryQO) {
        return adminLoginService.secretKeyLogin(devAccountQueryQO);
    }
}
