package com.socialuni.social.admin.controller;

import com.socialuni.social.admin.service.AdminLoginService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("socialuni/admin")
@Slf4j
public class AdminLoginController {
    @Resource
    AdminLoginService adminLoginService;

    //秘钥登录
    @PostMapping("secretKeyLogin")
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> secretKeyLogin(@RequestBody @Valid DevAccountInterface.DevAccountQueryQO devAccountQueryQO) {
        return adminLoginService.secretKeyLogin(devAccountQueryQO);
    }
}
