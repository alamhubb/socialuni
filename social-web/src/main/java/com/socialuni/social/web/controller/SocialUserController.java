package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialUserService;
import com.socialuni.social.model.model.QO.user.SocialUserIdQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.sdk.url.user.SocialuniUserUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialUserController implements SocialuniUserUrl {
    @Resource
    private SocialUserService socialUserService;

    @Override
    public ResultRO<SocialMineUserDetailRO> getMineUser() {
        return socialUserService.getMineUser();
    }

    @Override
    public ResultRO<SocialUserDetailRO> queryUserDetail(SocialUserIdQO socialUserIdQO) {
        return socialUserService.queryUserDetail(socialUserIdQO);
    }
}
