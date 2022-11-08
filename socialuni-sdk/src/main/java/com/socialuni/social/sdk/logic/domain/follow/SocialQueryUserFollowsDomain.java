package com.socialuni.social.sdk.logic.domain.follow;


import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.sdk.dao.DO.FollowDO;
import com.socialuni.social.sdk.dao.repository.FollowRepository;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserFollowDetailROFactory;
import com.socialuni.social.user.sdk.model.RO.SocialuniUserFollowDetailRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
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

    public Map<String, List<SocialuniUserFollowDetailRO>> queryUserFollows(SocialuniUserDo mineUser) {
        Map<String, List<SocialuniUserFollowDetailRO>> map = new HashMap<>();
        //查询他的关注
        List<FollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable);
        List<SocialuniUserDo> userDOS = followDOS.stream().map(followDO -> SocialuniUserUtil.getUserNotNull(followDO.getBeUserId())).collect(Collectors.toList());
        List<SocialuniUserFollowDetailRO> followUserVOS = SocialuniUserFollowDetailROFactory.getUsers(userDOS, mineUser);
        //查询他的粉丝
        List<FollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable);
        List<SocialuniUserDo> fansUserDOS = fans.stream().map(followDO -> SocialuniUserUtil.getUserNotNull(followDO.getUserId())).collect(Collectors.toList());
        List<SocialuniUserFollowDetailRO> fansUserVOS = SocialuniUserFollowDetailROFactory.getUsers(fansUserDOS, mineUser);
        map.put("follows", followUserVOS);
        map.put("fans", fansUserVOS);
        return map;
    }
}
