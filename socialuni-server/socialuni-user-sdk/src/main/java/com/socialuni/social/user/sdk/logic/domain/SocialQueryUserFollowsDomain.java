package com.socialuni.social.user.sdk.logic.domain;


import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.user.sdk.constant.SocialuniFollowPageType;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserFollowDetailROFactory;
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
    private SocialuniFollowRepository followRepository;

    public List<SocialuniUserFollowDetailListRO> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        String followPageType = socialuniPageQueryQO.getQueryData();

        if (SocialuniFollowPageType.follow.equals(followPageType)) {
//查询他的关注
            List<SocialuniUserFollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(mineUser.getUnionId(), SocialuniCommonStatus.enable, socialuniPageQueryQO.getQueryTime());
            List<SocialuniUserFollowDetailListRO> followUserVOS = SocialuniUserFollowDetailROFactory.getFollowUserLists(followDOS, mineUser);
            return followUserVOS;
        } else if (SocialuniFollowPageType.fans.equals(followPageType)) {
//查询他的粉丝
            List<SocialuniUserFollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(mineUser.getUnionId(), SocialuniCommonStatus.enable, socialuniPageQueryQO.getQueryTime());
            List<SocialuniUserFollowDetailListRO> fansUserVOS = SocialuniUserFollowDetailROFactory.getFansUserLists(fans, mineUser);
            return fansUserVOS;
        }
        throw new SocialSystemException("错误的关注页面类型");
    }
}
