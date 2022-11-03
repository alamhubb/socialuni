package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.sdk.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.entity.SocialUserFansDetailDo;
import com.socialuni.social.user.sdk.repository.SocialUserFansDetailRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserFansDetailRedis {
    @Resource
    SocialUserFansDetailRepository socialUserFansDetailApi;

    @Cacheable(cacheNames = CommonRedisKey.findUserFansDetailByUserId, unless = "#result == null")
    public SocialUserFansDetailDo findUserFansDetailByUserId(Integer userId) {
        return socialUserFansDetailApi.findByUserId(userId);
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = CommonRedisKey.userById, key = "#SocialUserFansDetailDo.userId")},
            put = {@CachePut(cacheNames = CommonRedisKey.findUserFansDetailByUserId, key = "#SocialUserFansDetailDo.userId")}
    )
    public SocialUserFansDetailDo save(SocialUserFansDetailDo SocialUserFansDetailDo) {
        return socialUserFansDetailApi.savePut(SocialUserFansDetailDo);
    }
}
