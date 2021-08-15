package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.QO.user.CenterUserEditQO;
import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.center.web.serive.CenterUserService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterUserController implements SocialuniUserAPI {

    @Resource
    private CenterUserService centerUserService;

    @Override
    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        return centerUserService.getMineUser();
    }

    @Override
    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        return centerUserService.queryUserDetail(centerUserIdQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return centerUserService.editUser(socialUserEditQO);
    }
}
