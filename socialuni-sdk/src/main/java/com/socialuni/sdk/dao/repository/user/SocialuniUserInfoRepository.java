package com.socialuni.sdk.dao.repository.user;

import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserSocialCoinDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUserInfoRepository<T extends SocialUnionContentBaseDO> extends JpaRepository<T, Integer> {
    T findByUserId(Integer userId);
}

