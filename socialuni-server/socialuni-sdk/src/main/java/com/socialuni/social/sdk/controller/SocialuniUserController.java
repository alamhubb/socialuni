package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.social.sdk.logic.service.user.SocialuniMineUserService;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.sdk.logic.service.user.SocialuniUserService;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserIdQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.user.SocialuniUserExtendFriendQueryQO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/user")
public class SocialuniUserController implements SocialuniUserAPI {
    @Resource
    private SocialuniMineUserService socialuniMineUserService;
    @Resource
    private SocialuniUserService socialuniUserService;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        return socialuniMineUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialuniUserDetailRO> queryUserDetail(String userId) {
        return socialuniUserService.queryUserDetail(userId);
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
    public ResultRO<SocialuniMineUserDetailRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        return socialuniMineUserService.addUserAvatarImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        return socialuniMineUserService.deleteUserImg(centerUserImgDeleteQO);
    }

    @Override
    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(String userId) {
        return socialuniUserService.getUserImgList(userId);
    }

    @Override
    public ResultRO<List<SocialuniContentUserRO>> queryRecentlyUsers() {
        return socialuniUserService.queryRecentlyUsers();
    }

    @Override
    public ResultRO<List<SocialuniUserDetailRO>> queryExtendFriendUsers(SocialuniPageQueryQO<SocialuniUserExtendFriendQueryQO>  socialuniPageQueryQO) {
        return socialuniUserService.queryExtendFriendUsers(socialuniPageQueryQO);
    }

    @Override
    public ResultRO<String> getUserContactInfo(String userId) {
        return socialuniUserService.getUserContactInfo(userId);
    }
}
