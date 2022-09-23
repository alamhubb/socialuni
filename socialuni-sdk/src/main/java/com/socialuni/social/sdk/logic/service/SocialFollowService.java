package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.sdk.logic.domain.follow.SocialQueryUserFollowsDomain;
import com.socialuni.social.sdk.logic.domain.follow.SocialUserFollowDomain;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.model.QO.community.SocialFollowAddQO;
import com.socialuni.social.common.model.ResultRO;
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

    public ResultRO<Map<String, List<SocialuniUserFollowDetailRO>>> queryUserFollows() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        Map<String, List<SocialuniUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows(mineUser);
        return new ResultRO<>(map);
    }
}
