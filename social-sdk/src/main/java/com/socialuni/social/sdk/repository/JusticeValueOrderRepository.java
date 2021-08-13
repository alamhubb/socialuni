package com.socialuni.social.sdk.repository;

import com.socialuni.social.entity.model.DO.JusticeValueOrderDO;
import com.socialuni.social.sdk.redis.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JusticeValueOrderRepository extends JpaRepository<JusticeValueOrderDO, Long> {
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#justiceValueOrder.userId")
    JusticeValueOrderDO save(JusticeValueOrderDO justiceValueOrder);
}

