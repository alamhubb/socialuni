package com.socialuni.sdk.api;

import com.socialuni.sdk.feignAPI.SocialuniFollowAPI;
import com.socialuni.sdk.model.QO.follow.CenterFollowAddQO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.sdk.serive.CenterFollowService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SocialuniFollowAPIImpl implements SocialuniFollowAPI {
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