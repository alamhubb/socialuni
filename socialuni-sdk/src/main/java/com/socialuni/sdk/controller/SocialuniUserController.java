package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.logic.service.user.SocialuniUserService;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniUserController implements SocialuniUserAPI {
    @Resource
    private SocialuniUserService centerUserAPIImpl;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        return centerUserAPIImpl.getMineUser();
    }

    @Override
    public ResultRO<SocialuniUserDetailRO> queryUserDetail(String userId) {
        return centerUserAPIImpl.queryUserDetail(userId);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return centerUserAPIImpl.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        return centerUserAPIImpl.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        return centerUserAPIImpl.deleteUserImg(centerUserImgDeleteQO);
    }
}
