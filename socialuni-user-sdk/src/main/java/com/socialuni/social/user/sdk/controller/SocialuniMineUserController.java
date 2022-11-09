package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.feignAPI.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.logic.service.SocialuniLoginService;
import com.socialuni.social.user.sdk.logic.service.SocialuniMineUserService;
import com.socialuni.social.user.sdk.logic.service.SocialuniPhoneService;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("socialuni/mineUser")
@RestController
public class SocialuniMineUserController implements SocialuniMineUserAPI {
    @Resource
    private SocialuniMineUserService socialuniMineUserService;
    @Resource
    private SocialuniLoginService socialuniLoginService;

    @Resource
    SocialuniPhoneService socialuniPhoneService;

    @Override
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        return socialuniPhoneService.sendAuthCode(authCodeQO);
    }

    @Override
    public ResultRO<SocialuniUserRO> getMineUser() {
        return socialuniMineUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialuniUserRO> editUser(SocialUserEditQO socialUserEditQO) {
        return socialuniMineUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<SocialuniUserRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniMineUserService.addUserAvatarImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserRO>> providerLogin(SocialProviderLoginQO loginData) {
        return socialuniLoginService.providerLogin(loginData);
    }

    @Override
    public ResultRO<SocialLoginRO<SocialuniUserRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        return socialuniLoginService.phoneLogin(socialPhoneNumQO);
    }
}
