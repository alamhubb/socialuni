package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniPhoneAPI;
import com.socialuni.social.sdk.logic.service.phone.SocialuniPhoneService;
import com.socialuni.social.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.RO.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("socialuni/phone")
public class SocialuniPhoneController implements SocialuniPhoneAPI {
    @Resource
    SocialuniPhoneService socialuniPhoneService;

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return socialuniPhoneService.sendAuthCode(authCodeQO);
    }
    @Override
    public ResultRO<SocialuniMineUserDetailRO> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return socialuniPhoneService.bindWxPhoneNum(bindWxPhoneNumQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> bindPhoneNum(@RequestBody @Valid SocialPhoneNumQO phoneNumQO) {
        return socialuniPhoneService.bindPhoneNum(phoneNumQO);
    }
}
