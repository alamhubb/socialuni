package com.socialuni.sdk.repository;

import com.socialuni.sdk.model.DO.JusticeValueOrderDO;
import com.socialuni.sdk.redis.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JusticeValueOrderRepository extends JpaRepository<JusticeValueOrderDO, Long> {
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#justiceValueOrder.userId")
    JusticeValueOrderDO save(JusticeValueOrderDO justiceValueOrder);
}

