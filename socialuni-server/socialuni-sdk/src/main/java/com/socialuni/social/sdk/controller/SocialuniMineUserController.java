package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniMineUserAPI;
import com.socialuni.social.sdk.logic.service.user.SocialuniMineUserService;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/mineUser")
public class SocialuniMineUserController implements SocialuniMineUserAPI {
    @Resource
    private SocialuniMineUserService socialuniUserService;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        return socialuniUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return socialuniUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniUserService.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniUserService.addUserAvatarImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        return socialuniUserService.deleteUserImg(centerUserImgDeleteQO);
    }
}
