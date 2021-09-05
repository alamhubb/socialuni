package com.socialuni.social.sdk.utils;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.mapper.TalkMapper;
import com.socialuni.social.sdk.redis.redisKey.RedisKeysConst;
import com.socialuni.social.sdk.redis.RedisKeysUtil;
import com.socialuni.social.sdk.repository.TalkRepository;
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

    //这里有问题，应该清楚所有引用了当前用户的
    @Cacheable(cacheNames = RedisKeysConst.queryUserFollowsTalkIds, key = "#userId+'-'+#userIds+'-'+#pageable.pageNumber+'-'+#pageable.pageSize")
    public List<Integer> queryUserFollowsTalkIds(Integer userId, List<Integer> userIds, Pageable pageable) {
        return talkRepository.queryTalkIdsByUserFollow(userId, ContentStatus.selfCanSeeContentStatus, userIds, ContentStatus.enable, pageable);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByTab, key = "#postTalkUserGender+'-'+#minAge+'-'+#maxAge+'-'+#adCode+'-'+#talkVisibleGender+'-'+#mineUserGender+'-'+#tagIds+'-'+#devId")
    public List<Integer> queryTalkIdsByTab(String postTalkUserGender,
                                           Integer minAge, Integer maxAge, String adCode,
                                           String talkVisibleGender,
                                           String mineUserGender, List<Integer> tagIds, Integer devId) {
//        log.info("queryTalkIdsByTab开始：" + new Date().getTime() / 1000);
        List<Integer> tagTalkIds;
        //    talkvisible	minegender	结果
        //    any	        null	    all,girl,boy
        //    all	        girl	    all,girl
        //    all	        boy	        all,boy
        //    girl	        girl	    girl
        //    boy	        boy	        boy
        //        新增talk时，这三个，查询talk的缓存都要清楚
        /*if (tagIds == null) {
            //talk性别相同或者user性别相同，能解决talk性别和user性别的问题，能查出来合集，如果为全部，则userGender为null
            tagTalkIds = talkRepository.queryTalkIdsByTagVisibleGender(talkVisibleGender, mineUserGender);
        } else {
            //不能自动自动添加应用tag，那样会导致应有所有tag的动态都查出来了
            tagTalkIds = talkRepository.queryTalkIdsByTagIdsAndTagVisibleGender(tagIds, talkVisibleGender, mineUserGender);
        }*/
//        log.info("queryTalkIdsByTagVisibleGender结束：" + new Date().getTime() / 1000);
//        List<Integer> userIds = userRepository.queryUserIdsByGenderAndAge(userGender, minAge, maxAge);

        /*List<Integer> talkIds = talkRepository.queryTalkIdsByAdCodeAndGender(ContentStatus.enable, adCode,
                talkGender, sessionUserGender, devId);*/
//        Integer devId = DevAccountUtils.getDevId();
        /*List<Integer> talkIds = talkRepository.queryTalkIdsByGenderAndAgeAndAdCodeAndGender(userGender, minAge, maxAge, ContentStatus.enable, adCode,
                talkGender, sessionUserGender, devId);*/

        /*List<Integer> talkIds = talkRepository.queryTalkIdsByCom(userGender, minAge, maxAge, ContentStatus.enable, adCode,
                talkVisibleGender, mineUserGender, tagIds, null);   */
        List<Integer> talkIds = talkMapper.queryTalkIdsByCom(postTalkUserGender, minAge, maxAge, ContentStatus.enable, adCode,
                talkVisibleGender, mineUserGender, tagIds, null);

//        log.info("queryTalkIdsByGenderAndAgeAndAdCodeAndGender结束：" + new Date().getTime() / 1000);

//        talkIds = filterTalkIds(talkIds, tagTalkIds);

        /*List<Integer> ids = talkRepository.queryTalkIdsTop10ByGenderAgeAndLikeAdCode(
                filterTalkIds, userIds);*/

       /* List<Integer> ids = talkRepository.queryTalkIdsTop10ByGenderAgeAndLikeAdCode(
                userId, userGender, minAge, maxAge, ContentStatus.enable, ContentStatus.preAudit, adCode,
                talkGender, sessionUserGender, devId);*/

//        List<Integer> ids = talkRepository.queryTalkIdsTop10ByGenderAgeAndLikeAdCode1();
//        log.info("queryTalkIdsByTab结束：" + new Date().getTime() / 1000);
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
