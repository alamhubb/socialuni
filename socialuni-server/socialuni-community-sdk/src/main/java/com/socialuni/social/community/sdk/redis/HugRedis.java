package com.socialuni.social.community.sdk.redis;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.community.sdk.dao.DO.SocialuniHugDO;
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
    public SocialuniHugDO save(SocialuniHugDO HugDO) {
        return hugApi.savePut(HugDO);
    }

    @Cacheable(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#userId+'-'+#talkId")
    public SocialuniHugDO findHugByTalkIdAndUserId(Long talkId, Long userId) {
        return hugApi.findByTalkIdAndUserId(talkId, userId);
    }
}
