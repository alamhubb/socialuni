package com.socialuni.social.user.sdk.dao.repository;

import com.socialuni.social.user.sdk.dao.DO.SocialuniUserOpenIdDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserOpenIdRepository extends JpaRepository<SocialuniUserOpenIdDO, Integer> {
    @Cacheable(cacheNames = "userOpenIdByUserId", key = "#userId")
    SocialuniUserOpenIdDO findByUserId(Integer userId);

    @Cacheable(cacheNames = "userOpenIdByUserOpenId", key = "#userOpenId")
    SocialuniUserOpenIdDO findByUserOpenId(String userOpenId);

    @Caching(
            put = {
                    @CachePut(cacheNames = "userOpenIdByUserId", key = "#socialuniUserOpenIdDO.userId"),
                    @CachePut(cacheNames = "userOpenIdByUserOpenId", key = "#socialuniUserOpenIdDO.userOpenId"),
            }
    )
    SocialuniUserOpenIdDO save(SocialuniUserOpenIdDO socialuniUserOpenIdDO);
}

