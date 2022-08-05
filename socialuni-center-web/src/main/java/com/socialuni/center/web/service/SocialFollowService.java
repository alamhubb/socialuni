package com.socialuni.center.web.service;


import com.socialuni.center.web.domain.follow.SocialQueryUserFollowsDomain;
import com.socialuni.center.web.domain.follow.SocialUserFollowDomain;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.QO.community.SocialFollowAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.user.base.SocialUserFollowDetailRO;
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
        Integer mineUserId = SocialUserUtil.getMineUserIdNotNull();
        socialUserFollowDomain.addFlow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialUserUtil.getMineUserIdNotNull();
        socialUserFollowDomain.cancelFollow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        Map<String, List<SocialUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows(mineUser);
        return new ResultRO<>(map);
    }
}
