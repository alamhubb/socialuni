package com.qingchi.web.controller.socialuni;

import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.service.PhoneService;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("phone")
public class PhoneController {
    @Resource
    PhoneService phoneService;

    @Deprecated
    @PostMapping("sendPhoneAuthCode")
    public ResultRO<Void> sendPhoneAuthCode(@RequestBody @Valid @NotBlank SocialSendAuthCodeQO socialSendAuthCodeQO) {
        return phoneService.sendAuthCode(socialSendAuthCodeQO);
    }

    @PostMapping("bindWxPhoneNum")
    public ResultRO<MineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return phoneService.bindWxPhoneNum(bindWxPhoneNumQO);
    }
}
