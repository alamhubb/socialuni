package com.socialuni.sdk.dao.repository.user.identity;


import com.socialuni.sdk.model.DO.user.SocialUserIdentityAuthDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialUserIdentityAuthRepository extends JpaRepository<SocialUserIdentityAuthDO, Integer> {
    @CachePut(cacheNames = "getUserIdentityInfo", key = "#socialUserIdentityAuthDO.userId")
    SocialUserIdentityAuthDO save(SocialUserIdentityAuthDO socialUserIdentityAuthDO);

    @Cacheable(cacheNames = "getUserIdentityInfo", key = "#userId")
    SocialUserIdentityAuthDO findFirstByUserId(Integer userId);

    List<SocialUserIdentityAuthDO> findTop20ByStatusInOrderByIdDesc(List<String> status);
}

