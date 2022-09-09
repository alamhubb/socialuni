package com.socialuni.sdk.logic.domain.follow;


import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniUserFollowDetailROFactory;
import com.socialuni.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.dao.DO.FollowDO;
import com.socialuni.sdk.dao.repository.FollowRepository;
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

    public Map<String, List<SocialuniUserFollowDetailRO>> queryUserFollows(SocialuniUserDO mineUser) {
        Map<String, List<SocialuniUserFollowDetailRO>> map = new HashMap<>();
        //查询他的关注
        List<FollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable);
        List<SocialuniUserDO> userDOS = followDOS.stream().map(followDO -> SocialuniUserUtil.getUserNotNull(followDO.getBeUserId())).collect(Collectors.toList());
        List<SocialuniUserFollowDetailRO> followUserVOS = SocialuniUserFollowDetailROFactory.getUsers(userDOS, mineUser);
        //查询他的粉丝
        List<FollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable);
        List<SocialuniUserDO> fansUserDOS = fans.stream().map(followDO -> SocialuniUserUtil.getUserNotNull(followDO.getUserId())).collect(Collectors.toList());
        List<SocialuniUserFollowDetailRO> fansUserVOS = SocialuniUserFollowDetailROFactory.getUsers(fansUserDOS, mineUser);
        map.put("follows", followUserVOS);
        map.put("fans", fansUserVOS);
        return map;
    }
}
