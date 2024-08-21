package com.socialuni.social.user.sdk.dao.repository;

import com.socialuni.social.user.sdk.dao.DO.SocialuniUserOnlineDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserOnlineRepository extends JpaRepository<SocialuniUserOnlineDO, Integer> {
    @Cacheable(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#userId")
    SocialuniUserOnlineDO findFirstByUserIdOrderByIdDesc(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniUserOnlineDO savePut(SocialuniUserOnlineDO socialuniUserExpandDO){
        return this.save(socialuniUserExpandDO);
    }
}

