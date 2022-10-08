package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserSocialCoinRepository extends JpaRepository<SocialuniUserSocialCoinDO, Integer> {
    @Cacheable(cacheNames = "userSocialCoinByUserId", key = "#userId")
    SocialuniUserSocialCoinDO findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userSocialCoinByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    SocialuniUserSocialCoinDO save(SocialuniUserSocialCoinDO socialuniUserExpandDO);

}

