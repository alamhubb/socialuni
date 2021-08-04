package com.socialuni.sdk.repository;


import com.socialuni.sdk.model.DO.user.SocialUserFansDetailDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDO, Integer> {

    @Cacheable(cacheNames = "findUserFollowByUserId")
    SocialUserFansDetailDO findByUserId(Integer userId);

    @CacheEvict(cacheNames = "findUserFollowByUserId", key = "#socialUserFansDetailDO.userId")
    SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO);
}

