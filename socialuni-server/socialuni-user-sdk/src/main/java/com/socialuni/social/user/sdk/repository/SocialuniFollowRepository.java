package com.socialuni.social.user.sdk.repository;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface SocialuniFollowRepository extends JpaRepository<SocialuniUserFollowDO, Integer> {
    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(evict = {
            //新增一条数据肯定所有数据清空，数据的显示数据变了
            @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#follow.userId"),
            //关注变化关注的人要变
            @CacheEvict(cacheNames = CommonRedisKey.queryUserFollowUserIds, key = "#follow.userId"),
            @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#follow.beUserId"),
            @CacheEvict(cacheNames = CommonRedisKey.queryUserAndBeUserFollow, key = "#follow.userId+'-'+#follow.beUserId")
    })
    SocialuniUserFollowDO save(SocialuniUserFollowDO follow);

    @Query("select f.beUserId from SocialuniUserFollowDO f where f.userId = :userId and f.status=:followStatus order by f.beUserId asc")
    List<Integer> queryUserFollowUserIds(
            @Param("userId") Integer userId,
            @Param("followStatus") String followStatus
    );

    SocialuniUserFollowDO findFirstByUserIdAndBeUserId(Integer userId, Integer beUserId);

    //查询他的关注
    List<SocialuniUserFollowDO> findTop30ByUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(Integer userId, String status, Date updateTime);

    //查询他的粉丝
    List<SocialuniUserFollowDO> findTop30ByBeUserIdAndStatusAndUpdateTimeLessThanOrderByUpdateTimeDesc(Integer beUserId, String status, Date updateTime);

    //查询他的粉丝
    List<SocialuniUserFollowDO> findAllByBeUserIdAndStatus(Integer userId, String status);
}


