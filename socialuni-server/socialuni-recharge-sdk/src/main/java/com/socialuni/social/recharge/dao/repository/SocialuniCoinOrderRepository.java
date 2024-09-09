package com.socialuni.social.recharge.dao.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniCoinOrderRepository extends JpaRepository<SocialuniCoinOrderDO, Integer> {

    @Cacheable(cacheNames = "findFirstByUserIdAndDetailType", key = "#userId+'-'+#detailType")
    SocialuniCoinOrderDO findFirstByUserIdAndDetailType(Long userId, String detailType);

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            put = {@CachePut(cacheNames = "findFirstByUserIdAndDetailType", key = "#socialuniCoinOrderDO.userId+'-'+socialuniCoinOrderDO.detailType")}
    )
    default SocialuniCoinOrderDO savePut(SocialuniCoinOrderDO socialuniCoinOrderDO) {
        return this.save(socialuniCoinOrderDO);
    }
}

