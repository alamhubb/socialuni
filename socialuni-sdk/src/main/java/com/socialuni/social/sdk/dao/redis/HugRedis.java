package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.sdk.constant.CommonRedisKey;
import com.socialuni.social.community.sdk.entity.HugDO;
import com.socialuni.social.community.sdk.repository.HugRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HugRedis {
    @Resource
    HugRepository hugApi;

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            put = {@CachePut(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#HugDO.userId+'-'+#HugDO.talkId")}
    )
    public HugDO save(HugDO HugDO) {
        return hugApi.savePut(HugDO);
    }

    @Cacheable(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#userId+'-'+#talkId")
    public HugDO findHugByTalkIdAndUserId(Integer talkId, Integer userId) {
        return hugApi.findByTalkIdAndUserId(talkId, userId);
    }
}
