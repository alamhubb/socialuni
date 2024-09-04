package com.socialuni.social.user.sdk.dao.repository;

import com.socialuni.social.user.sdk.dao.DO.SocialuniPartnerUserOnlineDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SocialuniUserOnlineRepository extends JpaRepository<SocialuniPartnerUserOnlineDO, Integer> {
    @Cacheable(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#userId")
    SocialuniPartnerUserOnlineDO findFirstByUserIdOrderByIdDesc(Long userId);

    @Caching(
            put = {@CachePut(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniPartnerUserOnlineDO savePut(SocialuniPartnerUserOnlineDO socialuniUserExpandDO) {
        return this.save(socialuniUserExpandDO);
    }

    List<SocialuniPartnerUserOnlineDO> findByTodayDate(String todayDate);


    List<SocialuniPartnerUserOnlineDO> findByUserIdAndCreateTimeBetween(Long userId, Date startTime, Date beginTime);
}

