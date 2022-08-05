package com.socialuni.center.web.utils;

import com.socialuni.center.web.redis.RedisKeysUtil;
import com.socialuni.center.web.redis.redisKey.RedisKeysConst;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.center.web.mapper.TalkMapper;
import com.socialuni.center.web.repository.community.TalkRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class TalkRedis {
    @Resource
    RedisUtil redisUtil;
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkMapper talkMapper;

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByTab, allEntries = true),
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByTagVisibleGender, allEntries = true),
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByTagIdsAndTagVisibleGender, allEntries = true),
//                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByAdCodeAndGender, allEntries = true),
                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByGenderAndAgeAndAdCodeAndGender, allEntries = true),
            },
            put = {@CachePut(cacheNames = RedisKeysConst.talkById, key = "#talkDO.id")}
    )
    public TalkDO save(TalkDO talkDO) {
        //清空用户的
        Set redisKeys = redisUtil.keys(RedisKeysUtil.getRedisKeys(RedisKeysConst.queryUserTalkIds, talkDO.getUserId() + "*"));
        //清空自己的
        redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(RedisKeysConst.queryMineTalkIds, talkDO.getUserId() + "*")));
        //清空关注自己的
        redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(RedisKeysConst.queryUserFollowsTalkIds, "*-" + talkDO.getUserId() + "-*")));
        //清除缓存
        redisUtil.del(redisKeys);
        return talkRepository.save(talkDO);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryUserTalkIds, key = "#userId+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryUserTalkIds(Integer userId, Pageable pageable) {
        return talkRepository.queryTalkIdsByUser(userId, ContentStatus.otherCanSeeContentStatus, pageable);
    }

    //用户发布动态后需要更新这个缓存
    @Cacheable(cacheNames = RedisKeysConst.queryMineTalkIds, key = "#userId+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryMineTalkIds(Integer userId, Pageable pageable) {
        return talkRepository.queryTalkIdsByUser(userId, ContentStatus.selfCanSeeContentStatus, pageable);
    }

    //用户发布动态后需要更新这个缓存
    @Cacheable(cacheNames = RedisKeysConst.queryMineTalkIdsByCom, key = "#userId+'-'+#circleId")
    public List<Integer> queryMineTalkIdsByCom(Integer userId, Integer circleId) {
        return talkMapper.queryMineTalkIdsByCom(userId, ContentStatus.selfCanSeeContentStatus, circleId);
    }

    //这里有问题，应该清楚所有引用了当前用户的
    @Cacheable(cacheNames = RedisKeysConst.queryUserFollowsTalkIds, key = "#userId+'-'+#userIds+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryUserFollowsTalkIds(Integer userId, List<Integer> userIds, Pageable pageable) {
        return talkRepository.queryTalkIdsByUserFollow(userId, ContentStatus.selfCanSeeContentStatus, userIds, ContentStatus.enable, pageable);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByTab, key = "#postTalkUserGender+'-'+#minAge+'-'+#maxAge+'-'+#adCode+'-'+#talkVisibleGender+'-'+#mineUserGender+'-'+#tagIds+'-'+#devId+'-'+#circleId+'-'+#hasPeopleImgTalkNeedIdentity")
    public List<Integer> queryTalkIdsByTab(String postTalkUserGender,
                                           Integer minAge, Integer maxAge, String adCode,
                                           String talkVisibleGender,
                                           String mineUserGender, List<Integer> tagIds, Integer devId, Integer circleId, Boolean hasPeopleImgTalkNeedIdentity) {
        List<Integer> talkIds = talkMapper.queryTalkIdsByCom(postTalkUserGender, minAge, maxAge, ContentStatus.enable, adCode,
                talkVisibleGender, mineUserGender, tagIds, null, circleId, hasPeopleImgTalkNeedIdentity);
        return talkIds;
    }


    public List<Integer> filterTalkIds(List<Integer> talkIds, List<Integer> tagTalkIds) {
        Map<Integer, Integer> talkIdMap = new HashMap<>();
        List<Integer> ids = new ArrayList<>();
        for (Integer talkId : talkIds) {
            talkIdMap.put(talkId, talkId);
        }
        for (Integer tagTalkId : tagTalkIds) {
            if (talkIdMap.get(tagTalkId) != null) {
                ids.add(tagTalkId);
            }
        }
        return ids;
    }
}
