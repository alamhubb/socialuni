package com.socialuni.social.partner.dao.repository;

import com.socialuni.social.partner.dao.model.SocialuniPartnerPointsMainDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniPartnerPointsMainRepository extends JpaRepository<SocialuniPartnerPointsMainDO, Integer> {
//    @Cacheable(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#userId")

    SocialuniPartnerPointsMainDO findFirstByTodayDateAndConsumeType(String today,String consumeType);


/*
    @Caching(
            put = {@CachePut(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniUserOnlineDO savePut(SocialuniUserOnlineDO socialuniUserExpandDO){
        return this.save(socialuniUserExpandDO);
    }*/
}

