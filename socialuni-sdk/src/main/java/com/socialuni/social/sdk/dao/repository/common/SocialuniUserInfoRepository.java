package com.socialuni.social.sdk.dao.repository.common;

import com.socialuni.social.sdk.dao.DO.base.SocialuniUserInfoBaseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SocialuniUserInfoRepository<T extends SocialuniUserInfoBaseDO> extends JpaRepository<T, Integer> {
   /* SocialuniUserInfoBaseDO findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userSocialCoinByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    SocialuniUserSocialCoinDO save(SocialuniUserSocialCoinDO socialuniUserExpandDO);*/

}

