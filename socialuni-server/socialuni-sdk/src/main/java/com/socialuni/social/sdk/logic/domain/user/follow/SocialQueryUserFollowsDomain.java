package com.socialuni.social.sdk.logic.domain.user.follow;


import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.sdk.constant.user.SocialuniFollowPageType;
import com.socialuni.social.sdk.dao.DO.SocialuniFollowDO;
import com.socialuni.social.sdk.dao.repository.FollowRepository;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserFollowDetailROFactory;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class SocialQueryUserFollowsDomain {
    @Resource
    private FollowRepository followRepository;

    public List<SocialuniUserFollowDetailListRO> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        String followPageType = socialuniPageQueryQO.getQueryData();

        if (SocialuniFollowPageType.follow.equals(followPageType)) {
//查询他的关注
            List<SocialuniFollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(mineUser.getUnionId(), SocialuniCommonStatus.init, socialuniPageQueryQO.getQueryTime());
            List<SocialuniUserFollowDetailListRO> followUserVOS = SocialuniUserFollowDetailROFactory.getFollowUserLists(followDOS, mineUser);
            return followUserVOS;
        } else if (SocialuniFollowPageType.fans.equals(followPageType)) {
//查询他的粉丝
            List<SocialuniFollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(mineUser.getUnionId(), SocialuniCommonStatus.init, socialuniPageQueryQO.getQueryTime());
            List<SocialuniUserFollowDetailListRO> fansUserVOS = SocialuniUserFollowDetailROFactory.getFansUserLists(fans, mineUser);
            return fansUserVOS;
        }
        throw new SocialSystemException("错误的关注页面类型");
    }
}
