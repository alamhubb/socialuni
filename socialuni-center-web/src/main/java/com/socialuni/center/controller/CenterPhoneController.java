package com.socialuni.center.controller;

import com.socialuni.center.service.PhoneService;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.sdk.serive.CenterPhoneService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("phone")
public class CenterPhoneController {
    @Resource
    CenterPhoneService centerPhoneService;
    @Resource
    PhoneService phoneService;

    @PostMapping("sendAuthCode")
    public ResultRO<Void> sendAuthCode(@RequestBody @Valid SocialSendAuthCodeQO authCodeQO){
        return centerPhoneService.sendAuthCode(authCodeQO);
    }

    @PostMapping("phoneLogin")
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO){
        return centerPhoneService.phoneLogin(socialPhoneNumQO);
    }

    @PostMapping("bindWxPhoneNum")
    public ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return phoneService.bindWxPhoneNum(bindWxPhoneNumQO);
    }

    @PostMapping("bindPhoneNum")
    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO) {
        return phoneService.bindPhoneNum(phoneNumQO);
    }
}
