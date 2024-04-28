package com.socialuni.social.friend.dao.repository

import com.socialuni.social.common.api.constant.CommonRedisKey
import com.socialuni.social.friend.dao.DO.SocialuniUserFollowDO
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Caching
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface SocialuniFollowRepository : JpaRepository<SocialuniUserFollowDO?, Int?> {
    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
        evict = [CacheEvict(
            cacheNames = [CommonRedisKey.userById],
            key = "#follow.userId"
        ), CacheEvict(
            cacheNames = [CommonRedisKey.queryUserFollowUserIds],
            key = "#follow.userId"
        ), CacheEvict(
            cacheNames = [CommonRedisKey.userById],
            key = "#follow.beUserId"
        ), CacheEvict(
            cacheNames = [CommonRedisKey.queryUserAndBeUserFollow],
            key = "#follow.userId+'-'+#follow.beUserId"
        )]
    )
    fun save(follow: SocialuniUserFollowDO): SocialuniUserFollowDO

    @Query("select f.beUserId from SocialuniUserFollowDO f where f.userId = :userId and f.status=:followStatus order by f.beUserId asc")
    fun queryUserFollowUserIds(
        @Param("userId") userId: Int?,
        @Param("followStatus") followStatus: String?
    ): List<Int?>?

    fun findFirstByUserIdAndBeUserId(userId: Int?, beUserId: Int?): SocialuniUserFollowDO?

    //查询他的关注
    fun findTop30ByUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(
        userId: Int?,
        status: String?,
        updateTime: Date?
    ): List<SocialuniUserFollowDO?>?

    //查询他的粉丝
    fun findTop30ByBeUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(
        beUserId: Int?,
        status: String?,
        updateTime: Date?
    ): List<SocialuniUserFollowDO?>?

    //查询他的粉丝
    fun findAllByBeUserIdAndStatus(userId: Int?, status: String?): List<SocialuniUserFollowDO?>?
}