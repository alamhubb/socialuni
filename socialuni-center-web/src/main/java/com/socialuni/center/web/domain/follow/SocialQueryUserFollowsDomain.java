package com.socialuni.center.web.domain.follow;


import com.socialuni.center.web.factory.user.base.SocialUserFollowDetailROFactory;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.center.web.model.DO.FollowDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.center.web.repository.FollowRepository;
import com.socialuni.center.web.utils.SocialUserUtil;
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

    public Map<String, List<SocialUserFollowDetailRO>> queryUserFollows(SocialUserDO mineUser) {
        Map<String, List<SocialUserFollowDetailRO>> map = new HashMap<>();
        //查询他的关注
        List<FollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable);
        List<SocialUserDO> userDOS = followDOS.stream().map(followDO -> SocialUserUtil.getNotNull(followDO.getBeUserId())).collect(Collectors.toList());
        List<SocialUserFollowDetailRO> followUserVOS = SocialUserFollowDetailROFactory.newUsers(userDOS, mineUser);
        //查询他的粉丝
        List<FollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable);
        List<SocialUserDO> fansUserDOS = fans.stream().map(followDO -> SocialUserUtil.getNotNull(followDO.getUserId())).collect(Collectors.toList());
        List<SocialUserFollowDetailRO> fansUserVOS = SocialUserFollowDetailROFactory.newUsers(fansUserDOS, mineUser);
        map.put("follows", followUserVOS);
        map.put("fans", fansUserVOS);
        return map;
    }
}