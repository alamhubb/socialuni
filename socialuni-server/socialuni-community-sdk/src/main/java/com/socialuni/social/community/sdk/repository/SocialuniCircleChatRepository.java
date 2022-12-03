package com.socialuni.social.community.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.community.sdk.entity.SocialuniCircleChatDO;
import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.enumeration.CircleRedisKey;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


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
