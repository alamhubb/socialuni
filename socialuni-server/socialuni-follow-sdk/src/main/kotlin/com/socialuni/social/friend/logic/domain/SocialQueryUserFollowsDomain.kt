package com.socialuni.social.user.sdk.logic.domain


import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus
import com.socialuni.social.common.api.exception.exception.SocialSystemException
import com.socialuni.social.common.api.model.SocialuniPageQueryQO
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO
import com.socialuni.social.user.sdk.constant.SocialuniFollowPageType
import com.socialuni.social.user.sdk.model.factory.SocialuniUserFollowDetailROFactory
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import javax.annotation.Resource
import java.util.List

@Component
@Slf4j
class SocialQueryUserFollowsDomain {
    @Resource
    private val followRepository: SocialuniFollowRepository? = null

    fun queryUserFollows(socialuniPageQueryQO: SocialuniPageQueryQO<String?>): List<SocialuniUserFollowDetailListRO> {
        val mineUser: SocialuniUserDo = SocialuniUserUtil.getMineUserNotNull()
        val followPageType: String = socialuniPageQueryQO.getQueryData()

        if (SocialuniFollowPageType.follow.equals(followPageType)) {
//查询他的关注
            val followDOS: List<SocialuniUserFollowDO> =
                followRepository.findTop30ByUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(
                    mineUser.getUnionId(),
                    SocialuniCommonStatus.enable,
                    socialuniPageQueryQO.getQueryTime()
                )
            val followUserVOS: List<SocialuniUserFollowDetailListRO> =
                SocialuniUserFollowDetailROFactory.getFollowUserLists(followDOS, mineUser)
            return followUserVOS
        } else if (SocialuniFollowPageType.fans.equals(followPageType)) {
//查询他的粉丝
            val fans: List<SocialuniUserFollowDO> =
                followRepository.findTop30ByBeUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(
                    mineUser.getUnionId(),
                    SocialuniCommonStatus.enable,
                    socialuniPageQueryQO.getQueryTime()
                )
            val fansUserVOS: List<SocialuniUserFollowDetailListRO> =
                SocialuniUserFollowDetailROFactory.getFansUserLists(fans, mineUser)
            return fansUserVOS
        }
        throw SocialSystemException("错误的关注页面类型")
    }
}
