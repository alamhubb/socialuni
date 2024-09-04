package com.socialuni.social.community.sdk.dao;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.dao.mapper.TalkMapper;
import com.socialuni.social.community.sdk.repository.TalkRepository;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.common.sdk.constant.RedisKeysUtil;
import com.socialuni.social.common.sdk.utils.RedisUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class SocialuniTalkDORedis {
    @Resource
    RedisUtil redisUtil;
    @Resource
    private TalkRepository talkApi;
    @Resource
    private TalkMapper talkMapper;

    @Caching(
            evict = {
//                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByAndUser, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByTalkCondition, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByAndUser, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByAndTag, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByAndCircle, allEntries = true),
                    @CacheEvict(cacheNames = "stickTalks", allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByAndUserExpand, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByTab, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByTagVisibleGender, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByTagIdsAndTagVisibleGender, allEntries = true),
//                    @CacheEvict(cacheNames = RedisKeysConst.queryTalkIdsByAdCodeAndGender, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.queryTalkIdsByGenderAndAgeAndAdCodeAndGender, allEntries = true),
            },
            put = {@CachePut(cacheNames = CommonRedisKey.talkById, key = "#talkDO.unionId")}
    )
    public SocialuniTalkDO save(SocialuniTalkDO talkDO) {
        //清空用户的
        Set redisKeys = redisUtil.keys(RedisKeysUtil.getRedisKeys(CommonRedisKey.queryUserTalkIds, talkDO.getUserId() + "*"));
        //清空自己的
        redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(CommonRedisKey.queryMineTalkIds, talkDO.getUserId() + "*")));
        //清空关注自己的
        redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(CommonRedisKey.queryUserFollowsTalkIds, "*-" + talkDO.getUserId() + "-*")));
        //清除缓存
        redisUtil.del(redisKeys);
        return talkApi.savePut(talkDO);
    }

    @Cacheable(cacheNames = CommonRedisKey.queryUserTalkIds, key = "#userId+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryUserTalkIds(Long userId, Pageable pageable) {
        return talkApi.queryTalkIdsByUser(userId, ContentStatus.otherCanSeeContentStatus, pageable);
    }

    //用户发布动态后需要更新这个缓存
    @Cacheable(cacheNames = CommonRedisKey.queryMineTalkIds, key = "#userId+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryMineTalkIds(Long userId, Pageable pageable) {
        return talkApi.queryTalkIdsByUser(userId, ContentStatus.selfCanSeeContentStatus, pageable);
    }

    //用户发布动态后需要更新这个缓存
    /*@Cacheable(cacheNames = RedisKeysConst.queryMineTalkIdsByCom, key = "#userId")
    public List<Integer> queryMineTalkIdsByCom(Long userId) {
        return talkMapper.queryMineTalkIdsByCom(userId, ContentStatus.selfCanSeeContentStatus);
    }*/

    //这里有问题，应该清楚所有引用了当前用户的
    @Cacheable(cacheNames = CommonRedisKey.queryUserFollowsTalkIds, key = "#userId+'-'+#userIds+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryUserFollowsTalkIds(Long userId, List<Integer> userIds, Pageable pageable) {
        return talkApi.queryTalkIdsByUserFollow(userId, ContentStatus.selfCanSeeContentStatus, userIds, ContentStatus.enable, pageable);
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
