package com.socialuni.social.sdk.dao.repository.user;

import com.socialuni.social.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
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

