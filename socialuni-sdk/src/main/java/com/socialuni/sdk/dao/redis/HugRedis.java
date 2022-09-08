package com.socialuni.sdk.dao.redis;

import com.socialuni.sdk.dao.redis.redisKey.RedisKeysConst;
import com.socialuni.sdk.dao.DO.HugDO;
import com.socialuni.sdk.dao.repository.HugRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HugRedis {
    @Resource
    HugRepository hugRepository;

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            evict = {@CacheEvict(cacheNames = RedisKeysConst.talkById, key = "#hugDO.talkId")},
            put = {@CachePut(cacheNames = RedisKeysConst.findHugByTalkIdAndUserId, key = "#hugDO.userId+'-'+#hugDO.talkId")}
    )
    public HugDO save(HugDO hugDO) {
        return hugRepository.save(hugDO);
    }

    @Cacheable(cacheNames = RedisKeysConst.findHugByTalkIdAndUserId, key = "#userId+'-'+#talkId")
    public HugDO findHugByTalkIdAndUserId(Integer talkId, Integer userId) {
        return hugRepository.findByTalkIdAndUserId(talkId, userId);
    }
}
