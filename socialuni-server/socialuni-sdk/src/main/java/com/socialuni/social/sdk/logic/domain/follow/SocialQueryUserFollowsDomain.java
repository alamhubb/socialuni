package com.socialuni.social.sdk.logic.domain.follow;


import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.sdk.constant.SocialuniFollowPageType;
import com.socialuni.social.sdk.dao.DO.FollowDO;
import com.socialuni.social.sdk.dao.repository.FollowRepository;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserFollowDetailROFactory;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SocialQueryUserFollowsDomain {
    @Resource
    private FollowRepository followRepository;

    public List<SocialuniUserFollowDetailRO> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        String followPageType = socialuniPageQueryQO.getQueryData();

        log.info("fensi:{}", followPageType);
        log.info("SocialuniFollowPageType:{}", SocialuniFollowPageType.fans);
        log.info("fensi:{}", followPageType.equals(SocialuniFollowPageType.fans));
        if (SocialuniFollowPageType.follow.equals(followPageType)) {
//查询他的关注
            List<FollowDO> followDOS = followRepository.findTop30ByUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable, socialuniPageQueryQO.getQueryTime());
            List<SocialuniUserDo> userDOS = followDOS.stream().map(followDO -> SocialuniUserUtil.getUserNotNull(followDO.getBeUserId())).collect(Collectors.toList());
            List<SocialuniUserFollowDetailRO> followUserVOS = SocialuniUserFollowDetailROFactory.getUsers(userDOS, mineUser);
            return followUserVOS;
        } else if (SocialuniFollowPageType.fans.equals(followPageType)) {
//查询他的粉丝
            List<FollowDO> fans = followRepository.findTop30ByBeUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(mineUser.getUnionId(), CommonStatus.enable, socialuniPageQueryQO.getQueryTime());
            List<SocialuniUserDo> fansUserDOS = fans.stream().map(followDO -> SocialuniUserUtil.getUserNotNull(followDO.getUserId())).collect(Collectors.toList());
            List<SocialuniUserFollowDetailRO> fansUserVOS = SocialuniUserFollowDetailROFactory.getUsers(fansUserDOS, mineUser);
            return fansUserVOS;
        }
        throw new SocialSystemException("错误的关注页面类型");
    }
}
