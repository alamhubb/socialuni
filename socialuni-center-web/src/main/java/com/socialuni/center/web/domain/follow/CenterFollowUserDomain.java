package com.socialuni.center.web.domain.follow;


import com.socialuni.center.web.model.QO.follow.CenterFollowAddQO;
import com.socialuni.center.web.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.center.web.factory.RO.user.CenterUserFollowDetailROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CenterFollowUserDomain {
    @Resource
    private SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;
    @Resource
    private SocialUserFollowDomain socialUserFollowDomain;

    public ResultRO<Void> addFlow(CenterFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = CenterUserUtil.getMineUserId();

        Integer followUserId = UnionIdDbUtil.getUserDOIdByUid(addVO.getBeUserId());

        socialUserFollowDomain.addFlow(mineUserId, followUserId);
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(CenterFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = CenterUserUtil.getMineUserId();
        Integer followUserId = UnionIdDbUtil.getUserDOIdByUid(addVO.getBeUserId());
        socialUserFollowDomain.cancelFollow(mineUserId, followUserId);
        return new ResultRO<>();
    }

    public Map<String, List<CenterUserFollowDetailRO>> queryUserFollows() {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        Map<String, List<SocialUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows(mineUser);

        Map<String, List<CenterUserFollowDetailRO>> centerMap = new HashMap<>();
        for (Map.Entry<String, List<SocialUserFollowDetailRO>> stringListEntry : map.entrySet()) {
            List<SocialUserFollowDetailRO> socialUserFollowDetailROS = stringListEntry.getValue();

            List<CenterUserFollowDetailRO> centerUserFollowDetailROS = CenterUserFollowDetailROFactory.getUsers(socialUserFollowDetailROS, mineUser);

            centerMap.put(stringListEntry.getKey(), centerUserFollowDetailROS);
        }
        return centerMap;
    }
}
