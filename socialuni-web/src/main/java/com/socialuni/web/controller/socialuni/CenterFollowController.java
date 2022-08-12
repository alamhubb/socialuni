package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.api.SocialuniFollowAPIImpl;
import com.socialuni.sdk.feignAPI.SocialuniFollowAPI;
import com.socialuni.sdk.model.QO.follow.CenterFollowAddQO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CenterFollowController implements SocialuniFollowAPI {
    @Resource
    SocialuniFollowAPIImpl centerFollowAPIImpl;

    @Override
    public ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows() {
        return centerFollowAPIImpl.queryUserFollows();
    }

    @Override
    public ResultRO<Void> addFollow(CenterFollowAddQO addVO) {
        return centerFollowAPIImpl.addFollow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(CenterFollowAddQO addVO) {
        return centerFollowAPIImpl.cancelFollow(addVO);
    }
}