package com.socialuni.social.sdk.dao.redis;

import com.socialuni.social.common.constant.CommonRedisKey;
import com.socialuni.social.community.sdk.model.HugModel;
import com.socialuni.social.community.sdk.api.HugApi;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HugRedis {
    @Resource
    HugApi hugApi;

    //关注后用户缓存修改，一人+粉丝，一人+关注
    @Caching(
            put = {@CachePut(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#hugModel.userId+'-'+#hugModel.talkId")}
    )
    public HugModel save(HugModel hugModel) {
        return hugApi.save(hugModel);
    }

    @Cacheable(cacheNames = CommonRedisKey.findHugByTalkIdAndUserId, key = "#userId+'-'+#talkId")
    public HugModel findHugByTalkIdAndUserId(Integer talkId, Integer userId) {
        return hugApi.findByTalkIdAndUserId(talkId, userId);
    }
}
