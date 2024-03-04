package com.socialuni.social.community.sdk.repository;

import com.socialuni.social.community.sdk.dao.DO.SocialuniCircleChatDO;
import com.socialuni.social.common.sdk.dao.redis.CircleRedisKey;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SocialuniCircleChatRepository extends JpaRepository<SocialuniCircleChatDO, Integer> {
    @Caching(
            put = {
                    @CachePut(cacheNames = CircleRedisKey.circleChatByName, key = "#tagDO.devId +  '-' + #tagDO.circleName"),
            }
    )
    SocialuniCircleChatDO save(SocialuniCircleChatDO tagDO);

    @Cacheable(cacheNames = CircleRedisKey.circleChatByName, key = "#devId + '-' + #circleName")
    SocialuniCircleChatDO findFirstByDevIdAndCircleName(Integer devId, String circleName);
}
