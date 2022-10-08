package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.api.SocialUserFansDetailRepository;
import com.socialuni.social.user.sdk.model.SocialUserFansDetailDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserFansDetailRedis {
    @Resource
    SocialUserFansDetailRepository socialUserFansDetailRepository;

    @Cacheable(cacheNames = CommonRedisKey.findUserFansDetailByUserId, unless = "#result == null")
    public SocialUserFansDetailDO findUserFansDetailByUserId(Integer userId) {
        return socialUserFansDetailRepository.findByUserId(userId);
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = CommonRedisKey.userById, key = "#socialUserFansDetailDO.userId")},
            put = {@CachePut(cacheNames = CommonRedisKey.findUserFansDetailByUserId, key = "#socialUserFansDetailDO.userId")}
    )
    public SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO) {
        return socialUserFansDetailRepository.save(socialUserFansDetailDO);
    }
}
