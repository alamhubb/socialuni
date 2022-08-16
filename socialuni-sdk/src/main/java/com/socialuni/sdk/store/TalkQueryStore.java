package com.socialuni.sdk.store;

import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.sdk.redis.FollowRedis;
import com.socialuni.sdk.repository.community.TalkRepository;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.utils.TalkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class TalkQueryStore {
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkRedis talkRedis;
    @Resource
    FollowRedis followRedis;

    public List<SocialTalkDO> queryTalksTop10ByUserFollow(List<Integer> talkIds, Integer userId) {
        List<Integer> beUserIds = followRedis.queryUserFollowUserIds(userId);
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryUserFollowsTalkIds(userId, beUserIds, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    public List<SocialTalkDO> queryUserTalks(SocialUserTalkQueryQO queryQO, SocialUserDO mineUser) {
        List<Integer> talkIds = queryQO.getTalkIds();
        Integer userId = queryQO.getUserId();

        if (ObjectUtils.isEmpty(talkIds)) {
            talkIds = Collections.singletonList(0);
        }
        List<SocialTalkDO> talks;
        if (mineUser != null && mineUser.getUnionId().equals(userId)) {
            talks = this.queryTalksTop10ByMine(talkIds, userId);
        } else {
            talks = this.queryTalksTop10ByUser(talkIds, userId);
        }
        return talks;
    }

    public List<SocialTalkDO> queryTalksTop10ByUser(List<Integer> talkIds, Integer userId) {
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryUserTalkIds(userId, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    //查看自己的动态，能查看到预审查状态的
    public List<SocialTalkDO> queryTalksTop10ByMine(List<Integer> talkIds, Integer userId) {
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryMineTalkIds(userId, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    public List<Integer> queryTalkIdsByTab(Integer mineUserId, String postTalkUserGender,
                                           Integer minAge, Integer maxAge, String adCode,
                                           String talkVisibleGender,
                                           String mineUserGender, List<Integer> tagIds, Pageable pageable, Integer devId, Date queryTime, Integer circleId, Boolean hasPeopleImgTalkNeedIdentity) {
//        log.info("queryUserTalkIdsByTab开始：" + new Date().getTime() / 1000);

//        为什么区分两个方法, 因为这个是通用的，下面那个是区分用户的，所以不一起缓存
        List<Integer> talkUnionIds = talkRedis.queryTalkIdsByTab(postTalkUserGender, minAge, maxAge, adCode,
                talkVisibleGender, mineUserGender, tagIds, devId, circleId, hasPeopleImgTalkNeedIdentity);

        if (mineUserId != null) {
            List<Integer> mineTalkIds = talkRedis.queryMineTalkIdsByCom(mineUserId, circleId);
            talkUnionIds.addAll(mineTalkIds);
        }

//        queryTime,

        //对id进行下排序，找到前10
        talkUnionIds = talkRepository.queryTalkIdsByIds(talkUnionIds, queryTime, pageable);
//        log.info("queryUserTalkIdsByTab结束：" + new Date().getTime() / 1000);
        return talkUnionIds;
    }

    public List<SocialTalkDO> queryTalksTop10ByGenderAgeAndLikeAdCodeAndTagIds(List<Integer> talkIds, Integer userId, String postTalkUserGender,
                                                                               Integer minAge, Integer maxAge, String adCode,
                                                                               List<Integer> tagIds, String talkVisibleGender,
                                                                               String mineUserGender, Integer devId, Date queryTime, Integer circleId, Boolean hasPeopleImgTalkNeedIdentity) {
//        int page = talkIds.size() / 10;
        int page = 0;
        List<Integer> ids = this.queryTalkIdsByTab(userId, postTalkUserGender, minAge, maxAge, adCode,
                talkVisibleGender, mineUserGender, tagIds, PageRequest.of(page, 10), devId, queryTime, circleId, hasPeopleImgTalkNeedIdentity);
        return this.queryTalksByIds(ids);
    }

    //根据id列表从缓存中读取talk列表
    public List<SocialTalkDO> queryTalksByIds(List<Integer> ids) {
        List<SocialTalkDO> talkDOS = new ArrayList<>();
        for (Integer id : ids) {
//            SocialTalkRO talkEO = TalkROFactory.newTalkRO(id);
//            talkDOS.add(talkEO);
            SocialTalkDO talkDO = TalkUtils.getNotNull(id);
            talkDOS.add(talkDO);
        }
        return talkDOS;
    }
}