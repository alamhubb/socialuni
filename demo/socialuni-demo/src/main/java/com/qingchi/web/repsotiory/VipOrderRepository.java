package com.qingchi.web.repsotiory;

import com.qingchi.web.model.DO.shell.VipOrderDO;
import com.socialuni.social.sdk.redis.redisKey.RedisKeysConst;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface VipOrderRepository extends JpaRepository<VipOrderDO, Integer> {
    //用户开通vip成功修改用户vip状态，清空缓存
    @CacheEvict(cacheNames = RedisKeysConst.userById, key = "#vipOrder.userId")
    VipOrderDO save(VipOrderDO vipOrder);

    Optional<VipOrderDO> findFirstByOrderNo(String orderNo);

//    VipOrderDO findFirstByEnabledAndUserOrderByIdDesc(Boolean enabled, Integer userId);
}


