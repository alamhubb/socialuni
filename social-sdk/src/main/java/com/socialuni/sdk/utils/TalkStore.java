package com.socialuni.sdk.utils;

import com.socialuni.entity.model.DO.talk.TalkDO;
import com.socialuni.sdk.redis.RedisKeysConst;
import com.socialuni.sdk.redis.RedisUtil;
import com.socialuni.sdk.repository.TalkRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TalkStore {
    @Resource
    RedisUtil redisUtil;
    @Resource
    private TalkRepository talkRepository;

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByTab, allEntries = true),
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByTagVisibleGender, allEntries = true),
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByTagIdsAndTagVisibleGender, allEntries = true),
//                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByAdCodeAndGender, allEntries = true),
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByGenderAndAgeAndAdCodeAndGender, allEntries = true),
            },
            put = {@CachePut(cacheNames = "talkById", key = "#talkDO.id")}
    )
    public TalkDO save(TalkDO talkDO) {
        //清空用户的
       /* Set redisKeys = redisUtil.keys(RedisKeysUtil.getRedisKeys(RedisKeysConst.queryUserTalkIds, talkDO.getUserId() + "*"));
        //清空自己的
        redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(RedisKeysConst.queryMineTalkIds, talkDO.getUserId() + "*")));
        //清空关注自己的
        redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(RedisKeysConst.queryUserFollowsTalkIds, "*" + talkDO.getUserId() + "*")));
        //清除缓存
        redisUtil.del(redisKeys);*/
        return talkRepository.save(talkDO);
    }
}
