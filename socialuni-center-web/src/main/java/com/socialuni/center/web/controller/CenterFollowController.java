package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniFollowAPI;
import com.socialuni.center.web.model.QO.follow.CenterFollowAddQO;
import com.socialuni.center.web.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.serive.CenterFollowService;
import com.socialuni.social.api.model.ResultRO;
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
        return centerFollowService.addFollow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(CenterFollowAddQO addVO) {
        return centerFollowService.cancelFollow(addVO);
    }
}