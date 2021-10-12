package com.socialuni.social.sdk.domain.follow;


import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.entity.model.DO.FollowDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.social.sdk.factory.user.base.SocialUserFollowDetailROFactory;
import com.socialuni.social.sdk.repository.FollowRepository;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SocialQueryUserFollowsDomain {
    @Resource
    private FollowRepository followRepository;

    public Map<String, List<SocialUserFollowDetailRO>> queryUserFollows(UserDO mineUser) {
        Map<String, List<SocialUserFollowDetailRO>> map = new HashMap<>();
        //查询他的关注
        List<FollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getId(), CommonStatus.enable);
        List<UserDO> userDOS = followDOS.stream().map(followDO -> SocialUserUtil.get(followDO.getBeUserId())).collect(Collectors.toList());
        List<SocialUserFollowDetailRO> followUserVOS = SocialUserFollowDetailROFactory.newUsers(userDOS, mineUser);
        //查询他的粉丝
        List<FollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getId(), CommonStatus.enable);
        List<UserDO> fansUserDOS = fans.stream().map(followDO -> SocialUserUtil.get(followDO.getUserId())).collect(Collectors.toList());
        List<SocialUserFollowDetailRO> fansUserVOS = SocialUserFollowDetailROFactory.newUsers(fansUserDOS, mineUser);
        map.put("follows", followUserVOS);
        map.put("fans", fansUserVOS);
        return map;
    }
}
