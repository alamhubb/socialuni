package com.socialuni.sdk.dao.repository;

import com.socialuni.sdk.dao.redis.redisKey.CommonRedisKey;
import com.socialuni.sdk.dao.DO.JusticeValueOrderDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JusticeValueOrderRepository extends JpaRepository<JusticeValueOrderDO, Long> {
    @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#justiceValueOrder.userId")
    JusticeValueOrderDO save(JusticeValueOrderDO justiceValueOrder);
}

