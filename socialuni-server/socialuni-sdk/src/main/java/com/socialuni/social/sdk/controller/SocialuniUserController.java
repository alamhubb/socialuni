package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.controller.SocialuniMineUserService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.user.sdk.api.user.SocialuniUserAPI;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.controller.SocialuniUserService;
import com.socialuni.social.userImg.model.SocialuniUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
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
    public ResultRO<SocialuniUserRO> queryUserDetail(Long userId) {
        return socialuniUserService.queryUserDetail(userId);
    }
}
