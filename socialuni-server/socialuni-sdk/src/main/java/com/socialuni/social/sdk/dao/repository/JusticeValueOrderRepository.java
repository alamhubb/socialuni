package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.sdk.dao.DO.match.JusticeValueOrderDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JusticeValueOrderRepository extends JpaRepository<JusticeValueOrderDO, Long> {
    @CacheEvict(cacheNames = CommonRedisKey.userById, key = "#justiceValueOrder.userId")
    JusticeValueOrderDO save(JusticeValueOrderDO justiceValueOrder);
}

