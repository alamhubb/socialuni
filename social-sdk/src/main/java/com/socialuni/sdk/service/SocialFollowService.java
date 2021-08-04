package com.socialuni.sdk.service;


import com.socialuni.sdk.domain.follow.SocialQueryUserFollowsDomain;
import com.socialuni.sdk.domain.follow.UserFollowDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.FollowRepository;
import com.socialuni.sdk.repository.SocialUserFansDetailRepository;
import com.socialuni.sdk.repository.UserRepository;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.FollowAddVO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SocialFollowService {
    @Resource
    private FollowRepository followRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    SocialUserFansDetailRepository socialUserFansDetailRepository;

    @Resource
    SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;
    @Resource
    private UserFollowDomain userFollowDomain;

    public ResultRO<Void> addFlow(FollowAddVO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialUserUtil.getMineUserId();
        userFollowDomain.addFlow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(FollowAddVO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialUserUtil.getMineUserId();
        userFollowDomain.cancelFollow(mineUserId, addVO.getBeUserId());
        return new ResultRO<>();
    }

    public ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        Map<String, List<SocialUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows(mineUser);
        return new ResultRO<>(map);
    }
}
