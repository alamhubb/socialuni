package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialFollowService;
import com.socialuni.social.model.model.QO.community.SocialFollowAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.social.sdk.url.community.SocialuniFollowUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SocialFollowController implements SocialuniFollowUrl {
    @Resource
    SocialFollowService socialFollowService;

    @Override
    public ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows() {
        return socialFollowService.queryUserFollows();
    }

    @Override
    public ResultRO<Void> addFollow(SocialFollowAddQO addVO) {
        return socialFollowService.addFlow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(SocialFollowAddQO addVO) {
        return socialFollowService.addFlow(addVO);
    }
}