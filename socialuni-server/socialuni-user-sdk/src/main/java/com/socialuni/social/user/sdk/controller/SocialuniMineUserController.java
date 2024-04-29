package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.api.user.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/mineUser")
public class SocialuniMineUserController implements SocialuniMineUserAPI {
    @Resource
    private SocialuniMineUserService socialuniMineUserService;

    @Override
    public ResultRO<SocialuniUserRO> getMineUser() {
        return socialuniMineUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialuniUserRO> editUser(SocialUserEditQO socialUserEditQO) {
        return socialuniMineUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<SocialuniUserRO> randomUserAvatar() {
        return socialuniMineUserService.randomUserAvatar();
    }

    @Override
    public ResultRO<SocialuniUserRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniMineUserService.addUserAvatarImg(socialUserImgAddQO);
    }
}
