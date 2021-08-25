package com.socialuni.social.sdk.store;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.redis.FollowRedis;
import com.socialuni.social.sdk.redis.RedisKeysConst;
import com.socialuni.social.sdk.repository.TalkRepository;
import com.socialuni.social.sdk.utils.TalkUtils;
import com.socialuni.social.model.model.QO.community.talk.SocialUserTalkQueryQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;

@Component
@Slf4j
public class TalkQueryStore {
    @Resource
    private TalkRepository talkRepository;
/*
    @Cacheable(cacheNames = RedisKeysConst.queryUserTalkIds, key = "#userId+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryUserTalkIds(Integer userId, Pageable pageable) {
        return socialContentStore.queryUserContentIdsByContentType(userId, ContentType.talk, pageable);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryMineTalkIds, key = "#userId+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryMineTalkIds(Integer userId, Pageable pageable) {
        return socialContentStore.queryMineContentIdsByContentType(userId, ContentType.talk, pageable);
    }*/


    @Resource
    FollowRedis followRedis;

    public List<TalkDO> queryTalksTop10ByUserFollow(List<Integer> talkIds, Integer userId) {
        List<Integer> beUserIds = followRedis.queryUserFollowUserIds(userId);
        int page = talkIds.size() / 10;
        List<Integer> ids = this.queryUserFollowsTalkIds(userId, beUserIds, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    public List<TalkDO> queryUserTalks(SocialUserTalkQueryQO queryQO, UserDO mineUser) {
        List<Integer> talkIds = queryQO.getTalkIds();
        Integer userId = queryQO.getUserId();

        if (ObjectUtils.isEmpty(talkIds)) {
            talkIds = Collections.singletonList(0);
        }
        List<TalkDO> talks;
        if (mineUser != null && mineUser.getId().equals(userId)) {
            talks = this.queryTalksTop10ByMine(talkIds, userId);
        } else {
            talks = this.queryTalksTop10ByUser(talkIds, userId);
        }
        return talks;
    }


    public List<TalkDO> queryTalksTop10ByUser(List<Integer> talkIds, Integer userId) {
        int page = talkIds.size() / 10;
        List<Integer> ids = this.queryUserTalkIds(userId, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    //查看自己的动态，能查看到预审查状态的
    public List<TalkDO> queryTalksTop10ByMine(List<Integer> talkIds, Integer userId) {
        int page = talkIds.size() / 10;
        List<Integer> ids = this.queryMineTalkIds(userId, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryUserTalkIds, key = "#userId+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryUserTalkIds(Integer userId, Pageable pageable) {
        return talkRepository.queryTalkIdsByUser(userId, ContentStatus.otherCanSeeContentStatus, pageable);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryMineTalkIds, key = "#userId+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryMineTalkIds(Integer userId, Pageable pageable) {
        return talkRepository.queryTalkIdsByUser(userId, ContentStatus.selfCanSeeContentStatus, pageable);
    }

    @Cacheable(cacheNames = RedisKeysConst.queryUserFollowsTalkIds, key = "#userId+'-'+#userIds+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryUserFollowsTalkIds(Integer userId, List<Integer> userIds, Pageable pageable) {
        return talkRepository.queryTalkIdsByUserFollow(userId, ContentStatus.selfCanSeeContentStatus, userIds, ContentStatus.enable, pageable);
    }

   /* @Cacheable(cacheNames = RedisKeysConst.queryUserFollowsTalkIds, key = "#userId+'-'+#userIds+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryUserFollowsTalkIds(Integer userId, List<Integer> userIds, Pageable pageable) {
        return talkRepository.queryTalkIdsByUserFollow(userId, ContentStatus.selfCanSeeContentStatus, userIds, ContentStatus.enable, pageable);
    }*/

    /*public List<TalkDO> queryTalksTop10ByGender(List<Integer> talkIds, Integer userId, List<String> genders) {
        List<Integer> ids = talkRepository.queryTalkIdsTop10ByGender(
                talkIds, userId, ContentStatus.preAudit, ContentStatus.otherCanSeeContentStatus, genders, PageRequest.of(0, 10));
        return this.queryTalksByIds(ids);
    }

    public List<TalkDO> queryTalksTop10ByGenderAgeAndLikeAdCode(List<Integer> talkIds, Integer userId, List<String> genders, Integer minAge, Integer maxAge, String adCode) {
        List<Integer> ids = talkRepository.queryTalkIdsTop10ByGenderAgeAndLikeAdCode(
                talkIds, userId, ContentStatus.preAudit, ContentStatus.otherCanSeeContentStatus, genders, minAge, maxAge, adCode, PageRequest.of(0, 10));
        return this.queryTalksByIds(ids);
    }*/


    public List<Integer> queryUserTalkIdsByTab(Integer userId, String userGender,
                                               Integer minAge, Integer maxAge, String adCode,
                                               String talkGender,
                                               String sessionUserGender, List<Integer> tagIds, Pageable pageable) {
//        log.info("queryUserTalkIdsByTab开始：" + new Date().getTime() / 1000);

        List<Integer> ids = this.queryTalkIdsByTab(userGender, minAge, maxAge, adCode,
                talkGender, sessionUserGender, tagIds, pageable);

        List<Integer> mineTalkIds = this.queryMineTalkIds(userId, PageRequest.of(0, 100));

        ids.addAll(mineTalkIds);

        ids = talkRepository.queryTalkIdsByIds(ids, pageable);
//        log.info("queryUserTalkIdsByTab结束：" + new Date().getTime() / 1000);
        return ids;
    }

    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByTab, key = "#userGender+'-'+#minAge+'-'+#maxAge+'-'+#adCode+'-'+#talkGender+'-'+#sessionUserGender+'-'+#devId+'-'+#tagIds+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    public List<Integer> queryTalkIdsByTab(String userGender,
                                           Integer minAge, Integer maxAge, String adCode,
                                           String talkGender,
                                           String sessionUserGender, List<Integer> tagIds, Pageable pageable) {
//        log.info("queryTalkIdsByTab开始：" + new Date().getTime() / 1000);
        List<Integer> tagTalkIds;
        if (tagIds == null) {
            //talk性别相同或者user性别相同，能解决talk性别和user性别的问题，能查出来合集
            tagTalkIds = talkRepository.queryTalkIdsByTagVisibleGender(talkGender, sessionUserGender);
        } else {
            tagTalkIds = talkRepository.queryTalkIdsByTagIdsAndTagVisibleGender(tagIds, talkGender, sessionUserGender);
        }
//        log.info("queryTalkIdsByTagVisibleGender结束：" + new Date().getTime() / 1000);
//        List<Integer> userIds = userRepository.queryUserIdsByGenderAndAge(userGender, minAge, maxAge);

        /*List<Integer> talkIds = talkRepository.queryTalkIdsByAdCodeAndGender(ContentStatus.enable, adCode,
                talkGender, sessionUserGender, devId);*/
//        Integer devId = DevAccountUtils.getDevId();
        /*List<Integer> talkIds = talkRepository.queryTalkIdsByGenderAndAgeAndAdCodeAndGender(userGender, minAge, maxAge, ContentStatus.enable, adCode,
                talkGender, sessionUserGender, devId);*/

        List<Integer> talkIds = talkRepository.queryTalkIdsByGenderAndAgeAndAdCodeAndGender(userGender, minAge, maxAge, ContentStatus.enable, adCode,
                talkGender, sessionUserGender, tagTalkIds, null);
//        log.info("queryTalkIdsByGenderAndAgeAndAdCodeAndGender结束：" + new Date().getTime() / 1000);

        talkIds = filterTalkIds(talkIds, tagTalkIds);

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

    public List<TalkDO> queryTalksTop10ByGenderAgeAndLikeAdCodeAndTagIds(List<Integer> talkIds, Integer userId, String userGender,
                                                                         Integer minAge, Integer maxAge, String adCode,
                                                                         List<Integer> tagIds, String talkGender,
                                                                         String sessionUserGender) {
        int page = talkIds.size() / 10;
        List<Integer> ids = this.queryUserTalkIdsByTab(userId, userGender, minAge, maxAge, adCode,
                talkGender, sessionUserGender, tagIds, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    //根据id列表从缓存中读取talk列表
    public List<TalkDO> queryTalksByIds(List<Integer> ids) {
        List<TalkDO> talkDOS = new ArrayList<>();
        for (Integer id : ids) {
//            SocialTalkRO talkEO = TalkROFactory.newTalkRO(id);
//            talkDOS.add(talkEO);
            TalkDO talkDO = TalkUtils.get(id);
            talkDOS.add(talkDO);
        }
        return talkDOS;
    }
}