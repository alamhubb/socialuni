package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.api.user.SocialuniMineUserAPI;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Resource
    SocialuniTokenManage tokenManage;
    /**
     * 刷新token
     *
     * @return
     */
    @GetMapping("refreshToken")
    public ResultRO<SocialLoginRO<SocialuniUserRO>> refreshToken() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        ResultRO<SocialuniUserRO> resultRO = socialuniMineUserService.getMineUser();

        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());
        return ResultRO.success(new SocialLoginRO<>(socialUserTokenDO.getToken(), resultRO.getData()));
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
