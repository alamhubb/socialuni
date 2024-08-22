package com.socialuni.social.partner.dao.repository;

import com.socialuni.social.partner.dao.model.SocialuniPartnerPointsMainDO;
import com.socialuni.social.partner.dao.model.SocialuniPartnerUserContributeDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniPartnerUserContributeRepository extends JpaRepository<SocialuniPartnerUserContributeDO, Integer> {
//    @Cacheable(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#userId")

    SocialuniPartnerUserContributeDO findFirstByUserIdAndTodayDate(Integer userId, String today);


/*
    @Caching(
            put = {@CachePut(cacheNames = "findFirstByUserIdOrderByIdDesc", key = "#socialuniUserExpandDO.userId")}
    )
    default SocialuniUserOnlineDO savePut(SocialuniUserOnlineDO socialuniUserExpandDO){
        return this.save(socialuniUserExpandDO);
    }*/
}

