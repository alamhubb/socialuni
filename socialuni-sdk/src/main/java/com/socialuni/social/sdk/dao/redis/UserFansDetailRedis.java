package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.api.SocialUserFansDetailApi;
import com.socialuni.social.user.sdk.model.SocialUserFansDetailModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserFansDetailRedis {
    @Resource
    SocialUserFansDetailApi socialUserFansDetailApi;

    @Cacheable(cacheNames = CommonRedisKey.findUserFansDetailByUserId, unless = "#result == null")
    public SocialUserFansDetailModel findUserFansDetailByUserId(Integer userId) {
        return socialUserFansDetailApi.findByUserId(userId);
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = CommonRedisKey.userById, key = "#socialUserFansDetailModel.userId")},
            put = {@CachePut(cacheNames = CommonRedisKey.findUserFansDetailByUserId, key = "#socialUserFansDetailModel.userId")}
    )
    public SocialUserFansDetailModel save(SocialUserFansDetailModel socialUserFansDetailModel) {
        return socialUserFansDetailApi.save(socialUserFansDetailModel);
    }
}
