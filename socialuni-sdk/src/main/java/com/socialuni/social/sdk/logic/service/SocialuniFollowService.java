package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.feignAPI.community.SocialuniFollowAPI;
import com.socialuni.social.sdk.logic.domain.follow.SocialuniFollowUserDomain;
import com.socialuni.social.sdk.model.QO.follow.SocialuniFollowAddQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.common.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SocialuniFollowService {
    @Resource
    private SocialuniFollowUserDomain centerFollowUserDomain;

    @Resource
    SocialuniFollowAPI socialuniFollowAPI;

    public ResultRO<Void> addFollow(SocialuniFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        centerFollowUserDomain.addFlow(addQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniFollowAPI.addFollow(addQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialuniFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        centerFollowUserDomain.cancelFollow(addQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniFollowAPI.cancelFollow(addQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<Map<String, List<SocialuniUserFollowDetailRO>>> queryUserFollows() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniFollowAPI.queryUserFollows();
        }
        Map<String, List<SocialuniUserFollowDetailRO>> map = centerFollowUserDomain.queryUserFollows();
        return new ResultRO<>(map);
    }
}
