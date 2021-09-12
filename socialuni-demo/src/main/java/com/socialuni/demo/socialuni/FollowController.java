package com.socialuni.demo.socialuni;

import com.socialuni.api.feignAPI.SocialuniFollowAPI;
import com.socialuni.api.model.QO.follow.CenterFollowAddQO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class FollowController implements SocialuniFollowAPI {
    @Resource
    SocialuniFollowAPI socialuniFollowAPI;

    @Override
    public ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows() {
        return socialuniFollowAPI.queryUserFollows();
    }

    @Override
    public ResultRO<Void> addFollow(CenterFollowAddQO addVO) {
        return socialuniFollowAPI.addFollow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(CenterFollowAddQO addVO) {
        return socialuniFollowAPI.cancelFollow(addVO);
    }
}