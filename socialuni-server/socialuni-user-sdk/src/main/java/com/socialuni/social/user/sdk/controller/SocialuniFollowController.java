package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.user.sdk.api.SocialuniFollowAPI;
import com.socialuni.social.user.sdk.logic.service.SocialuniFollowService;
import com.socialuni.social.user.sdk.model.QO.follow.SocialuniUserFollowAddQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/follow")
public class SocialuniFollowController implements SocialuniFollowAPI {
    @Resource
    SocialuniFollowService centerFollowService;

    @Override
    public ResultRO<List<SocialuniUserFollowDetailListRO>> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        ResultRO<List<SocialuniUserFollowDetailListRO>> resultRO = centerFollowService.queryUserFollows(socialuniPageQueryQO);
        return resultRO;
    }

    @Override
    public ResultRO<Void> addFollow(SocialuniUserFollowAddQO addVO) {
        return centerFollowService.addFollow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(SocialuniUserFollowAddQO addVO) {
        return centerFollowService.cancelFollow(addVO);
    }
}