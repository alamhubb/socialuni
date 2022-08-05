package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniUserAPI;
import com.socialuni.center.web.model.QO.user.CenterUserIdQO;
import com.socialuni.center.web.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.model.RO.user.CenterUserDetailRO;
import com.socialuni.center.web.serive.CenterUserService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.center.web.model.QO.user.SocialUserEditQO;
import com.socialuni.center.web.model.QO.user.SocialUserImgAddQO;
import com.socialuni.center.web.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.center.web.model.RO.user.SocialUserIdentityAuthRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterUserController implements SocialuniUserAPI {

    @Resource
    private CenterUserService centerUserService;

    @Override
    public ResultRO<CenterMineUserDetailRO> registryUser(SocialProviderLoginQO loginQO) {
        return centerUserService.registryUser(loginQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        return centerUserService.getMineUser();
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> queryThirdUser() {
        return centerUserService.queryThirdUser();
    }

    @Override
    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        return centerUserService.queryUserDetail(centerUserIdQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return centerUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        return centerUserService.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        return centerUserService.deleteUserImg(centerUserImgDeleteQO);
    }
}
