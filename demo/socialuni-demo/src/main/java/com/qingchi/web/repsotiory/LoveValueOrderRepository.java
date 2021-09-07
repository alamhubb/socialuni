package com.qingchi.web.repsotiory;

import com.qingchi.web.model.DO.user.LoveValueOrderDO;
import com.socialuni.social.sdk.redis.redisKey.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface LoveValueOrderRepository extends JpaRepository<LoveValueOrderDO, Long> {
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#loveValue.userId")
    LoveValueOrderDO save(LoveValueOrderDO loveValue);

    Integer countByUserIdAndStatusAndEnableAndAwardAndCreateTimeBetween(Integer userId, String status, Boolean enable, Boolean award, Date startDate, Date endDate);
}

