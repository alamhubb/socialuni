package com.socialuni.sdk.logic.service;


import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.feignAPI.SocialuniFollowAPI;
import com.socialuni.sdk.model.QO.follow.CenterFollowAddQO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.sdk.logic.domain.follow.CenterFollowUserDomain;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CenterFollowService {
    @Resource
    private CenterFollowUserDomain centerFollowUserDomain;

    @Resource
    SocialuniFollowAPI socialuniFollowAPI;

    public ResultRO<Void> addFollow(CenterFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        centerFollowUserDomain.addFlow(addQO);
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniFollowAPI.addFollow(addQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(CenterFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        centerFollowUserDomain.cancelFollow(addQO);
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniFollowAPI.cancelFollow(addQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniFollowAPI.queryUserFollows();
        }
        Map<String, List<CenterUserFollowDetailRO>> map = centerFollowUserDomain.queryUserFollows();
        return new ResultRO<>(map);
    }
}
