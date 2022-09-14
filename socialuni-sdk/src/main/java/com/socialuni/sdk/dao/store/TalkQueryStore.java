package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.mapper.TalkMapper;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.sdk.dao.redis.FollowRedis;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.utils.TalkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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
    @Resource
    private TalkMapper talkMapper;

    public List<SocialTalkDO> queryTalksTop10ByUserFollow(List<Integer> talkIds, Integer userId) {
        List<Integer> beUserIds = followRedis.queryUserFollowUserIds(userId);
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryUserFollowsTalkIds(userId, beUserIds, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    public List<SocialTalkDO> queryUserTalks(SocialUserTalkQueryQO queryQO, SocialuniUserDO mineUser) {
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

    public List<SocialTalkDO> queryTalksTop10ByGenderAgeAndLikeAdCodeAndTagIds(SocialHomeTabTalkQueryBO queryBO) {
        String talkUserGender = queryBO.getTalkUserGender();
        //sql需要，为all改为null
        if (GenderType.all.equals(talkUserGender)) {
            talkUserGender = null;
        }
        String talkVisibleGender = queryBO.getTalkVisibleGender();

        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        String mineUserGender = null;
        if (mineUser != null) {
            mineUserGender = mineUser.getGender();
        }
        //查询所有talkId
        //需要连接用户表查询，后面不需要重复筛选，因为已经基础过滤出来了这些值，后面与合并逻辑，所以不需要在过滤
        List<Integer> userTalkUnionIds = talkMapper.queryTalkIdsByAndUser(talkUserGender, queryBO.getMinAge(), queryBO.getMaxAge(), ContentStatus.enable, queryBO.getAdCode(),
                talkVisibleGender, mineUserGender, null, queryBO.getQueryTime());

        List<Integer> tagIds = queryBO.getTagIds();
        //没选择tag的情况，
        if (!CollectionUtils.isEmpty(tagIds)) {
            List<Integer> tagTalkUnionIds = talkMapper.queryTalkIdsByAndTag(tagIds);
            //取交集
            userTalkUnionIds.retainAll(tagTalkUnionIds);
        }
        Integer circleId = queryBO.getCircleId();
        if (circleId != null) {
            List<Integer> queryTalkUnionIds = talkMapper.queryTalkIdsByAndCircle(circleId);
            //取交集
            userTalkUnionIds.retainAll(queryTalkUnionIds);
        }

        Boolean userHasSchool = queryBO.getUserHasSchoolNam();
        //看有没有设置onlyschooname,有的话连用户扩展表查询
        if (userHasSchool != null && userHasSchool) {
            List<Integer> queryTalkUnionIds = talkMapper.queryTalkIdsByAndUserExpand();
            //取交集
            userTalkUnionIds.retainAll(queryTalkUnionIds);
        }
        if (mineUser != null) {
            Integer mineUserId = mineUser.getUnionId();
            if (mineUserId != null) {
                List<Integer> queryTalkUnionIds = talkRedis.queryMineTalkIdsByCom(mineUserId);
                //取交集
                userTalkUnionIds.retainAll(queryTalkUnionIds);
            }
        }
        if (userTalkUnionIds.size() > 10) {
            userTalkUnionIds = userTalkUnionIds.subList(0, 10);
        }
        return this.queryTalksByIds(userTalkUnionIds);
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