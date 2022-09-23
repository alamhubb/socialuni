package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.DO.MatchRequestDO;
import com.socialuni.sdk.dao.redis.redisKey.CommonRedisKey;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequestDO, Integer> {
    @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#matchRequest.receiveUserId")
    MatchRequestDO save(MatchRequestDO matchRequest);

    MatchRequestDO queryMatchRequestByUserIdAndReceiveUserIdAndStatus(Integer userId, Integer receiveUserId, String status);

    MatchRequestDO queryMatchRequestByUserIdAndReceiveUserId(Integer userId, Integer receiveUserId);
}