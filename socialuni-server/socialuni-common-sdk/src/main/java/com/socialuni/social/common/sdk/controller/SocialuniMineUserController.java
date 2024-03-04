package com.socialuni.social.common.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.common.sdk.feignAPI.user.SocialuniMineUserAPI;
import com.socialuni.social.common.sdk.logic.service.user.SocialuniMineUserService;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/mineUser")
public class SocialuniMineUserController implements SocialuniMineUserAPI {
    @Resource
    private SocialuniMineUserService socialuniMineUserService;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        return socialuniMineUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return socialuniMineUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniMineUserService.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> randomUserAvatar() {
        return socialuniMineUserService.randomUserAvatar();
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniMineUserService.addUserAvatarImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        return socialuniMineUserService.deleteUserImg(centerUserImgDeleteQO);
    }
}
