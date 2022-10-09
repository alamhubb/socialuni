package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.sdk.config.SocialuniAppConfig;
import com.socialuni.social.sdk.config.SocialuniAppConfigBO;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.dao.mapper.TalkMapper;
import com.socialuni.social.sdk.dao.redis.FollowRedis;
import com.socialuni.social.sdk.dao.repository.community.TalkRepository;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import com.socialuni.social.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.social.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
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
    private SocialuniTalkDORedis talkRedis;
    @Resource
    FollowRedis followRedis;
    @Resource
    private TalkMapper talkMapper;

    public List<SocialuniTalkDO> queryTalksTop10ByUserFollow(List<Integer> talkIds, Integer userId) {
        List<Integer> beUserIds = followRedis.queryUserFollowUserIds(userId);
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryUserFollowsTalkIds(userId, beUserIds, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    public List<SocialuniTalkDO> queryUserTalks(SocialUserTalkQueryQO queryQO, SocialuniUserDO mineUser) {
        List<Integer> talkIds = queryQO.getTalkIds();
        Integer userId = queryQO.getUserId();

        if (ObjectUtils.isEmpty(talkIds)) {
            talkIds = Collections.singletonList(0);
        }
        List<SocialuniTalkDO> talks;
        if (mineUser != null && mineUser.getUnionId().equals(userId)) {
            talks = this.queryTalksTop10ByMine(talkIds, userId);
        } else {
            talks = this.queryTalksTop10ByUser(talkIds, userId);
        }
        return talks;
    }

    public List<SocialuniTalkDO> queryTalksTop10ByUser(List<Integer> talkIds, Integer userId) {
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryUserTalkIds(userId, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    //查看自己的动态，能查看到预审查状态的
    public List<SocialuniTalkDO> queryTalksTop10ByMine(List<Integer> talkIds, Integer userId) {
        int page = talkIds.size() / 10;
        List<Integer> ids = talkRedis.queryMineTalkIds(userId, PageRequest.of(page, 10));
        return this.queryTalksByIds(ids);
    }

    public List<SocialuniTalkDO> queryTalksTop10ByGenderAgeAndLikeAdCodeAndTagIds(SocialHomeTabTalkQueryBO queryBO) {
        String talkUserGender = queryBO.getTalkUserGender();
        //sql需要，为all改为null
        if (GenderType.all.equals(talkUserGender)) {
            talkUserGender = null;
        }

        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        String mineUserGender = null;
        if (mineUser != null) {
            mineUserGender = mineUser.getGender();
        }

        SocialuniAppConfigBO appConfig = SocialuniAppConfig.getAppConfig();


        //是否禁止未成年人内容
        Boolean disableUnderageContent = null;
        if (appConfig.getDisableUnderageContent()) {
            disableUnderageContent = true;
        }

        //是否禁止内容中包含联系方式
        Boolean disableContentHasContactInfo = null;
        if (appConfig.getDisableContentHasContactInfo()) {
            disableContentHasContactInfo = true;
        }

        //是否禁止内容中包含二维码
        Boolean disableContentHasQrCode = null;
        if (appConfig.getDisableContentHasQrCode()) {
            disableContentHasQrCode = true;
        }

        //为什么要用程序过滤，为了多缓存内容
        //查询所有talkId
        //需要连接用户表查询，后面不需要重复筛选，因为已经基础过滤出来了这些值，后面与合并逻辑，所以不需要在过滤
        List<Integer> userTalkUnionIds = talkMapper.queryTalkIdsByAndUser(talkUserGender, queryBO.getMinAge(), queryBO.getMaxAge(), ContentStatus.enable, appConfig.getDisableUnderageContent());
        List<Integer> talkUnionIds = talkMapper.queryTalkIdsByTalkCondition(
                ContentStatus.enable, queryBO.getAdCode(),
                queryBO.getTalkVisibleGender(), mineUserGender, null,
                disableUnderageContent,
                disableContentHasContactInfo,
                disableContentHasQrCode);
        //取交集

        userTalkUnionIds = ListConvertUtil.intersection(userTalkUnionIds, talkUnionIds);
        //可以多次查询同一个表

        List<Integer> tagIds = queryBO.getTagIds();
        //没选择tag的情况，
        if (!CollectionUtils.isEmpty(tagIds)) {
            List<Integer> tagTalkUnionIds = talkMapper.queryTalkIdsByAndTag(tagIds);
            //取交集
            userTalkUnionIds = ListConvertUtil.intersection(userTalkUnionIds, tagTalkUnionIds);
        }
        Integer circleId = queryBO.getCircleId();
        if (circleId != null) {
            List<Integer> queryTalkUnionIds = talkMapper.queryTalkIdsByAndCircle(circleId);
            //取交集
            userTalkUnionIds = ListConvertUtil.intersection(userTalkUnionIds, queryTalkUnionIds);
        }
        Boolean userHasSchool = queryBO.getUserHasSchoolNam();
        //看有没有设置onlyschooname,有的话连用户扩展表查询
        if (userHasSchool != null && userHasSchool) {
            List<Integer> queryTalkUnionIds = talkMapper.queryTalkIdsByAndUserExpand();
            //取交集
            userTalkUnionIds = ListConvertUtil.intersection(userTalkUnionIds, queryTalkUnionIds);
        }
        PageRequest pageRequest = PageRequest.of(0, 10);

        if (!CollectionUtils.isEmpty(queryBO.getTalkIds())) {
            int page = queryBO.getTalkIds().size() / 10;
            pageRequest = PageRequest.of(page, 10);
        }
        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
        if (mineUserId != null) {
            List<Integer> queryTalkUnionIds = talkRedis.queryMineTalkIds(mineUserId, pageRequest);
            //取交集
            userTalkUnionIds.addAll(queryTalkUnionIds);
        }

        if (userTalkUnionIds.size() > 900) {
            userTalkUnionIds = userTalkUnionIds.subList(0, 900);
        }

        userTalkUnionIds = talkRepository.queryTalkIdsByIds(userTalkUnionIds, queryBO.getQueryTime(), pageRequest);
        /*if (userTalkUnionIds.size() > 10) {
            userTalkUnionIds = userTalkUnionIds.subList(0, 10);
        }*/
        return this.queryTalksByIds(userTalkUnionIds);
    }

    //根据id列表从缓存中读取talk列表
    public List<SocialuniTalkDO> queryTalksByIds(List<Integer> ids) {
        List<SocialuniTalkDO> talkDOS = new ArrayList<>();
        for (Integer id : ids) {
//            SocialTalkRO talkEO = TalkROFactory.newTalkRO(id);
//            talkDOS.add(talkEO);
            SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(id);
            talkDOS.add(talkDO);
        }
        return talkDOS;
    }
}