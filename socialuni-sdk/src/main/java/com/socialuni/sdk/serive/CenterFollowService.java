package com.socialuni.sdk.serive;


import com.socialuni.sdk.model.QO.follow.CenterFollowAddQO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.sdk.domain.follow.CenterFollowUserDomain;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CenterFollowService {
    @Resource
    private CenterFollowUserDomain centerFollowUserDomain;

    public ResultRO<Void> addFollow(CenterFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        centerFollowUserDomain.addFlow(addQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(CenterFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        centerFollowUserDomain.cancelFollow(addQO);
        return new ResultRO<>();
    }

    public ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows() {
        Map<String, List<CenterUserFollowDetailRO>> map = centerFollowUserDomain.queryUserFollows();
        return new ResultRO<>(map);
    }
}
