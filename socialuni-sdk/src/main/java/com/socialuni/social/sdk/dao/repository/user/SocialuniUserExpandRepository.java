package com.socialuni.social.sdk.dao.repository.user;

import com.socialuni.social.sdk.dao.DO.user.SocialuniUserExpandDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserExpandRepository extends JpaRepository<SocialuniUserExpandDO, Integer> {
    @Cacheable(cacheNames = "userExpandByUserId", key = "#userId")
    SocialuniUserExpandDO findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userExpandByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    SocialuniUserExpandDO save(SocialuniUserExpandDO socialuniUserExpandDO);

}

