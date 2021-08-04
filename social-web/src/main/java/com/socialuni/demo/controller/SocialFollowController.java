package com.socialuni.demo.controller;

import com.socialuni.sdk.service.SocialFollowService;
import com.socialuni.social.model.model.QO.community.FollowAddVO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.sdk.url.community.SocialuniFollowAPI;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SocialFollowController implements SocialuniFollowAPI {
    @Resource
    SocialFollowService socialFollowService;

    @Override
    public ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows() {
        return socialFollowService.queryUserFollows();
    }

    @Override
    public ResultRO<Void> addFollow(FollowAddVO addVO) {
        return socialFollowService.addFlow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(FollowAddVO addVO) {
        return socialFollowService.addFlow(addVO);
    }
}