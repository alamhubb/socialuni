package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.sdk.dao.DO.match.MatchRequestDO;
import com.socialuni.social.common.api.constant.CommonRedisKey;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequestDO, Integer> {
    @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#matchRequest.receiveUserId")
    MatchRequestDO save(MatchRequestDO matchRequest);

    MatchRequestDO queryMatchRequestByUserIdAndBeUserIdAndStatus(Long userId, Integer receiveUserId, String status);

    MatchRequestDO queryMatchRequestByUserIdAndBeUserId(Long userId, Integer receiveUserId);
}