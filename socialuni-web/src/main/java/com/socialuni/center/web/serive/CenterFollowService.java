package com.socialuni.center.web.serive;


import com.socialuni.api.model.QO.follow.CenterFollowAddQO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.domain.follow.CenterFollowUserDomain;
import com.socialuni.social.api.model.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CenterFollowService {
    @Resource
    private CenterFollowUserDomain centerFollowUserDomain;

    public ResultRO<Void> addFlow(CenterFollowAddQO addQO) {
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
