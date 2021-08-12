package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniFollowAPI;
import com.socialuni.api.model.QO.follow.CenterFollowAddQO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.serive.CenterFollowService;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CenterFollowController implements SocialuniFollowAPI {
    @Resource
    CenterFollowService centerFollowService;

    @Override
    public ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows() {
        return centerFollowService.queryUserFollows();
    }

    @Override
    public ResultRO<Void> addFollow(CenterFollowAddQO addVO) {
        return centerFollowService.addFlow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(CenterFollowAddQO addVO) {
        return centerFollowService.addFlow(addVO);
    }
}