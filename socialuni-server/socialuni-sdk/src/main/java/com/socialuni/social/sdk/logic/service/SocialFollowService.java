package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.logic.domain.user.follow.SocialQueryUserFollowsDomain;
import com.socialuni.social.sdk.logic.domain.user.follow.SocialUserFollowDomain;
import com.socialuni.social.sdk.model.QO.community.SocialFollowAddQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialFollowService {
    @Resource
    SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;
    @Resource
    private SocialUserFollowDomain socialUserFollowDomain;

    public ResultRO<Void> addFlow(SocialFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        socialUserFollowDomain.addFlow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        socialUserFollowDomain.cancelFollow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

//    public ResultRO<Map<String, List<SocialuniUserFollowDetailRO>>> queryUserFollows() {
//        Map<String, List<SocialuniUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows();
//        return new ResultRO<>(map);
//    }
}
