package com.socialuni.center.web.redis;

import com.socialuni.center.web.redis.redisKey.RedisKeysConst;
import com.socialuni.center.web.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.center.web.repository.user.SocialUserFansDetailRepository;
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

    @Cacheable(cacheNames = RedisKeysConst.findUserFansDetailByUserId, unless = "#result == null")
    public SocialUserFansDetailDO findUserFansDetailByUserId(Integer userId) {
        return socialUserFansDetailRepository.findByUserId(userId);
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = RedisKeysConst.userById, key = "#socialUserFansDetailDO.userId")},
            put = {@CachePut(cacheNames = RedisKeysConst.findUserFansDetailByUserId, key = "#socialUserFansDetailDO.userId")}
    )
    public SocialUserFansDetailDO save(SocialUserFansDetailDO socialUserFansDetailDO) {
        return socialUserFansDetailRepository.save(socialUserFansDetailDO);
    }
}
