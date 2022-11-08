package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.community.SocialuniFollowAPI;
import com.socialuni.social.sdk.logic.service.SocialuniFollowService;
import com.socialuni.social.sdk.model.QO.follow.SocialuniFollowAddQO;
import com.socialuni.social.user.sdk.model.RO.SocialuniUserFollowDetailRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("socialuni/follow")
public class SocialuniFollowController implements SocialuniFollowAPI {
    @Resource
    SocialuniFollowService centerFollowService;

    @Override
    public ResultRO<Map<String, List<SocialuniUserFollowDetailRO>>> queryUserFollows() {
        return centerFollowService.queryUserFollows();
    }

    @Override
    public ResultRO<Void> addFollow(SocialuniFollowAddQO addVO) {
        return centerFollowService.addFollow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(SocialuniFollowAddQO addVO) {
        return centerFollowService.cancelFollow(addVO);
    }
}