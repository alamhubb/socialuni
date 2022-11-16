package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.social.sdk.logic.service.user.SocialuniMineUserService;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/user")
public class SocialuniUserController implements SocialuniUserAPI {
    @Resource
    private SocialuniMineUserService socialuniUserService;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        return socialuniUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialuniUserDetailRO> queryUserDetail(String userId) {
        return socialuniUserService.queryUserDetail(userId);
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

    @Override
    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(String userId) {
        return socialuniUserService.getUserImgList(userId);
    }
}
