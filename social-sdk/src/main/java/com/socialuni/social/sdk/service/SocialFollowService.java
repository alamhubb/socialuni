package com.socialuni.social.sdk.service;


import com.socialuni.social.sdk.domain.follow.SocialQueryUserFollowsDomain;
import com.socialuni.social.sdk.domain.follow.SocialUserFollowDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.SocialFollowAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SocialFollowService {
    @Resource
    SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;
    @Resource
    private SocialUserFollowDomain socialUserFollowDomain;

    public ResultRO<Void> addFlow(SocialFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialUserUtil.getMineUserId();
        socialUserFollowDomain.addFlow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialUserUtil.getMineUserId();
        socialUserFollowDomain.cancelFollow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        Map<String, List<SocialUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows(mineUser);
        return new ResultRO<>(map);
    }
}
