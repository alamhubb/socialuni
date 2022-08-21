package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.service.AdminPhoneService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("phone")
public class AdminPhoneController {
    @Resource
    AdminPhoneService adminPhoneService;

    /**
     * 腾讯云手机验证码相关，手机号登陆和绑定都使用这里发送验证码
     *
     */
    @PostMapping("sendAuthCode")
    public ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO socialSendAuthCodeQO) {
        return adminPhoneService.sendAuthCode(socialSendAuthCodeQO);
    }
}
