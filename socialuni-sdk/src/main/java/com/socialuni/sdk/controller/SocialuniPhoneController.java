package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.user.SocialuniPhoneAPI;
import com.socialuni.sdk.logic.service.phone.SocialuniPhoneService;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
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
