package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.dev.api.SocialuniNoUseFeignAspect;
import com.socialuni.social.user.sdk.api.user.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/mineUser")
public class SocialuniMineUserController implements SocialuniMineUserAPI {
    @Resource
    private SocialuniMineUserService socialuniMineUserService;

    @Override
    public ResultRO<SocialuniUserShowRO> getMineUser() {
        return socialuniMineUserService.getMineUser();
    }

    /**
     * 刷新token
     *
     * @return
     */
    @GetMapping("refreshToken")
    @SocialuniNoUseFeignAspect
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> refreshToken() {
        return socialuniMineUserService.refreshToken();
    }

    @Override
    public ResultRO<SocialuniUserShowRO> editUser(SocialUserEditQO socialUserEditQO) {
        return socialuniMineUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<SocialuniUserShowRO> randomUserAvatar() {
        return socialuniMineUserService.randomUserAvatar();
    }

    @Override
    public ResultRO<SocialuniUserShowRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniMineUserService.addUserAvatarImg(socialUserImgAddQO);
    }
}
