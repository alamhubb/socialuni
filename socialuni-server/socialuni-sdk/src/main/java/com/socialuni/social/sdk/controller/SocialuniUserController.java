package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.user.sdk.controller.SocialuniMineUserService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.api.user.SocialuniUserAPI;
import com.socialuni.social.user.sdk.controller.SocialuniUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/user")
public class SocialuniUserController implements SocialuniUserAPI {
    @Resource
    private SocialuniMineUserService socialuniMineUserService;
    @Resource
    private SocialuniUserService socialuniUserService;

    @Override
    public ResultRO<SocialuniUserShowRO> queryUserDetail(Long userId) {
        return socialuniUserService.queryUserDetail(userId);
    }
}
