package com.socialuni.social.friend.dao.redis

import com.socialuni.social.common.api.constant.CommonRedisKey
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede
import com.socialuni.social.friend.dao.DO.SocialuniUserFollowDO
import com.socialuni.social.friend.dao.repository.SocialuniFollowRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
@Component
class SocialuniUserFollowRedis {
    @Resource
    var followRepository: SocialuniFollowRepository? = null

    @Cacheable(cacheNames = [CommonRedisKey.queryUserFollowUserIds], key = "#userId")
    fun queryUserFollowUserIds(userId: Int?): List<Int?>? {
        return followRepository!!.queryUserFollowUserIds(userId, SocialuniCommonStatus.enable)
    }

    //    @Cacheable(cacheNames = CommonRedisKey.queryUserAndBeUserFollow, key = "#userId+'-'+#beUserId")
    fun findFirstByUserIdAndBeUserId(userId: Int?, beUserId: Int?): SocialuniUserFollowDO {
//        return followRepository.findFirstByUserIdAndBeUserId(userId, beUserId);
        return SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId<SocialuniUserFollowDO>(
            userId, beUserId,
            SocialuniUserFollowDO::class.java
        )
    }
}