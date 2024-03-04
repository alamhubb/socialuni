package com.socialuni.social.common.sdk.dao.repository;

import com.socialuni.social.common.sdk.dao.DO.MatchRequestDO;
import com.socialuni.social.common.api.constant.CommonRedisKey;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRequestRepository extends JpaRepository<MatchRequestDO, Integer> {
    @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#matchRequest.receiveUserId")
    MatchRequestDO save(MatchRequestDO matchRequest);

    MatchRequestDO queryMatchRequestByUserIdAndBeUserIdAndStatus(Integer userId, Integer receiveUserId, String status);

    MatchRequestDO queryMatchRequestByUserIdAndBeUserId(Integer userId, Integer receiveUserId);
}