package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.logic.service.user.SocialuniUserService;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniUserController implements SocialuniUserAPI {
    @Resource
    private SocialuniUserService socialuniUserService;

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
