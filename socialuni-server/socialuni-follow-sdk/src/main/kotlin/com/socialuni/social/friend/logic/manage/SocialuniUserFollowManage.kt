package com.socialuni.social.user.sdk.logic.manage

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus
import com.socialuni.social.friend.dao.DO.SocialuniUserFollowDO
import com.socialuni.social.friend.dao.repository.SocialuniFollowRepository
import org.springframework.stereotype.Component
import javax.annotation.Resource
import java.util.Date

@Component
class SocialuniUserFollowManage {
    @Resource
    private val followRepository: SocialuniFollowRepository? = null

    fun createFollow(mineUserId: Integer?, beUserId: Integer?): SocialuniUserFollowDO {
        var followDO: SocialuniUserFollowDO = SocialuniUserFollowDO()
        //两个用户粉丝和关注各加1
        followDO.setUserId(mineUserId)
        followDO.setBeUserId(beUserId)
        followDO = followRepository.save(followDO)
        return followDO
    }

    fun updateFollow(followDO: SocialuniUserFollowDO, status: String?): SocialuniUserFollowDO {
        var followDO: SocialuniUserFollowDO = followDO
        followDO.setStatus(status)
        followDO.setUpdateTime(Date())
        followDO = followRepository.save(followDO)
        return followDO
    }

    fun userHasFollowBeUser(userId: Integer?, beUserId: Integer?): Boolean {
        val followDO: SocialuniUserFollowDO = followRepository.findFirstByUserIdAndBeUserId(userId, beUserId)
        return followDO != null && SocialuniCommonStatus.enable.equals(followDO.getStatus())
    }
}
