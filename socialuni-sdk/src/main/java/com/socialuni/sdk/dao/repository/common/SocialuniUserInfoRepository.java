package com.socialuni.sdk.dao.repository.common;

import com.socialuni.sdk.dao.DO.base.SocialuniUserInfoBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@NoRepositoryBean
public interface SocialuniUserInfoRepository<T extends SocialuniUserInfoBaseDO> extends JpaRepository<T, Integer> {
   /* SocialuniUserInfoBaseDO findByUserId(Integer userId);

    @Caching(
            put = {@CachePut(cacheNames = "userSocialCoinByUserId", key = "#socialuniUserExpandDO.userId")}
    )
    SocialuniUserSocialCoinDO save(SocialuniUserSocialCoinDO socialuniUserExpandDO);*/

}

