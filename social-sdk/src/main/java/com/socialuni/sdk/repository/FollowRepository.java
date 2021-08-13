package com.socialuni.sdk.repository;

import com.socialuni.entity.model.DO.FollowDO;
import com.socialuni.sdk.redis.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface FollowRepository extends JpaRepository<FollowDO, Integer> {
    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(evict = {
            //新增一条数据肯定所有数据清空，数据的显示数据变了
            @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#follow.userId"),
            //关注变化关注的人要变
            @CacheEvict(cacheNames = RedisKeysConst.queryUserFollowUserIds, key = "#follow.userId"),
            @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#follow.beUserId")
    })
    FollowDO save(FollowDO follow);

    @Query("select f.beUserId from FollowDO f where f.userId = :userId and f.status=:followStatus order by f.beUserId asc")
    List<Integer> queryUserFollowUserIds(
            @Param("userId") Integer userId,
            @Param("followStatus") String followStatus
    );

    FollowDO findFirstByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status);
    FollowDO findFirstByUserIdAndBeUserId(Integer userId, Integer beUserId);

    Integer countByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status);

    //查出来以后在外面判断的是否为生效
    Optional<FollowDO> findFirstByUserIdAndBeUserIdOrderByIdDesc(Integer userId, Integer beUserId);

    //查询他的关注
    List<FollowDO> findTop30ByUserIdAndStatusOrderByUpdateTimeDesc(Integer userId, String status);
    //查询他的粉丝
    List<FollowDO> findTop30ByBeUserIdAndStatusOrderByUpdateTimeDesc(Integer beUserId, String status);

    //查询他的粉丝
    List<FollowDO> findAllByBeUserIdAndStatus(Integer userId, String status);
}


