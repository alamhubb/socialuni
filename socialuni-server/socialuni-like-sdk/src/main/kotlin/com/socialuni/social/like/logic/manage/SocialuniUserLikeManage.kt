package com.socialuni.social.like.logic.manage

import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede
import com.socialuni.social.follow.dao.DO.SocialuniUserFollowDO
import com.socialuni.social.like.dao.DO.SocialuniUserLikeDO
import org.springframework.stereotype.Component
import java.util.*

@Component
class SocialuniUserLikeManage {
    fun createUserLike(mineUserId: Int?, beUserId: Int?): SocialuniUserLikeDO {
        var userLikeDO = SocialuniUserLikeDO()
        //添加喜欢
        userLikeDO.userId = mineUserId
        userLikeDO.beUserId = beUserId
        userLikeDO = SocialuniUserContactRepositoryFacede.save(userLikeDO)
        return userLikeDO
    }

    fun updateLikeStatue(userLikeDO: SocialuniUserLikeDO, status: String?): SocialuniUserLikeDO {
        var userLikeDO = userLikeDO
        userLikeDO.status = status
        userLikeDO.updateTime = Date()
        userLikeDO = SocialuniUserContactRepositoryFacede.save(userLikeDO)
        return userLikeDO
    }
}
