package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.MatchRequestDO;
import com.socialuni.sdk.redis.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequestDO, Integer> {
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#matchRequest.receiveUserId")
    MatchRequestDO save(MatchRequestDO matchRequest);

    MatchRequestDO queryMatchRequestByUserIdAndReceiveUserIdAndStatus(Integer userId, Integer receiveUserId, String status);

    MatchRequestDO queryMatchRequestByUserIdAndReceiveUserId(Integer userId, Integer receiveUserId);
}