package com.socialuni.social.sdk.repository;


import com.socialuni.social.entity.model.DO.user.SocialUserFansDetailDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDO, Integer> {

    @Cacheable(cacheNames = "findUserFollowByUserId", unless = "#result == null")
    SocialUserFansDetailDO findByUserId(Integer userId);

    @CacheEvict(cacheNames = "findUserFollowByUserId", key = "#socialUserFansDetailDO.userId")
    SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO);
}

