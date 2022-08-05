package com.socialuni.center.web.repository;

import com.socialuni.center.web.redis.redisKey.RedisKeysConst;
import com.socialuni.social.entity.model.DO.JusticeValueOrderDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JusticeValueOrderRepository extends JpaRepository<JusticeValueOrderDO, Long> {
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#justiceValueOrder.userId")
    JusticeValueOrderDO save(JusticeValueOrderDO justiceValueOrder);
}

