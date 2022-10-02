package com.socialuni.social.sdk.logic.domain.follow;


import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.follow.SocialuniFollowAddQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class SocialuniFollowUserDomain {
    @Resource
    private SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;
    @Resource
    private SocialUserFollowDomain socialUserFollowDomain;

    public ResultRO<Void> addFlow(SocialuniFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer followUserId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(addVO.getBeUserId());

        socialUserFollowDomain.addFlow(mineUserId, followUserId);
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialuniFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        Integer followUserId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(addVO.getBeUserId());
        socialUserFollowDomain.cancelFollow(mineUserId, followUserId);
        return new ResultRO<>();
    }

    public Map<String, List<SocialuniUserFollowDetailRO>> queryUserFollows() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        Map<String, List<SocialuniUserFollowDetailRO>> map = socialQueryUserFollowsDomain.queryUserFollows(mineUser);

        return map;
    }
}