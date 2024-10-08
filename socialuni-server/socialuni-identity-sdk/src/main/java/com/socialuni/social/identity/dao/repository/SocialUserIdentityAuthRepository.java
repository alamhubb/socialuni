package com.socialuni.social.identity.dao.repository;


import com.socialuni.social.identity.dao.DO.SocialUserIdentityAuthDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialUserIdentityAuthRepository extends JpaRepository<SocialUserIdentityAuthDO, Integer> {
    @CachePut(cacheNames = "getUserIdentityInfo", key = "#socialUserIdentityAuthDO.userId")
    SocialUserIdentityAuthDO save(SocialUserIdentityAuthDO socialUserIdentityAuthDO);

    @Cacheable(cacheNames = "getUserIdentityInfo", key = "#userId")
    SocialUserIdentityAuthDO findFirstByUserId(Long userId);

    List<SocialUserIdentityAuthDO> findTop20ByStatusInOrderByIdDesc(List<String> status);
}

