package com.socialuni.social.admin.controller;

import com.socialuni.social.admin.service.AdminPhoneService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
