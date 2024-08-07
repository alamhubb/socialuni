package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.user.sdk.api.user.SocialuniPhoneAPI;
import com.socialuni.social.sdk.logic.service.phone.SocialuniPhoneBindService;
import com.socialuni.social.user.sdk.logic.service.SocialuniPhoneService;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import com.socialuni.social.user.sdk.utils.SocialuniMineUserPhoneUtil;
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
    @Resource
    SocialuniPhoneBindService socialuniPhoneBindService;

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return socialuniPhoneService.sendAuthCode(authCodeQO);
    }

    @Override
    public ResultRO<String> bindWxPhoneNum(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        return socialuniPhoneBindService.bindWxPhoneNum(bindWxPhoneNumQO);
    }

    @Override
    public ResultRO<String> bindPhoneNum(@RequestBody @Valid SocialPhoneNumAuthCodeQO phoneNumQO) {
        return socialuniPhoneBindService.bindPhoneNum(phoneNumQO);
    }

    @Override
    public ResultRO<Boolean> checkRegistry(SocialSendAuthCodeQO authCodeQO) {
        return socialuniPhoneService.checkRegistry(authCodeQO);
    }

    @Override
    public ResultRO<String> getMineUserPhoneNum() {
        return ResultRO.success(SocialuniMineUserPhoneUtil.getMineUserPhoneNum());
    }
}
