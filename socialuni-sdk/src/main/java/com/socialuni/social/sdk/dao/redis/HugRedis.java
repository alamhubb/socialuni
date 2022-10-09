package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.constant.CommonRedisKey;
import com.socialuni.social.sdk.dao.DO.HugDO;
import com.socialuni.social.sdk.dao.repository.HugRepository;
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
            put = {@CachePut(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#hugDO.userId+'-'+#hugDO.talkId")}
    )
    public HugDO save(HugDO hugDO) {
        return hugRepository.save(hugDO);
    }

    @Cacheable(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#userId+'-'+#talkId")
    public HugDO findHugByTalkIdAndUserId(Integer talkId, Integer userId) {
        return hugRepository.findByTalkIdAndUserId(talkId, userId);
    }
}
