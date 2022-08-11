package com.socialuni.sdk.repository;

import com.socialuni.sdk.model.DO.MatchRequestDO;
import com.socialuni.sdk.redis.redisKey.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequestDO, Integer> {
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#matchRequest.receiveUserId")
    MatchRequestDO save(MatchRequestDO matchRequest);

    MatchRequestDO queryMatchRequestByUserIdAndReceiveUserIdAndStatus(Integer userId, Integer receiveUserId, String status);

    MatchRequestDO queryMatchRequestByUserIdAndReceiveUserId(Integer userId, Integer receiveUserId);
}