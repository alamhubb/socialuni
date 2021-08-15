package com.socialuni.social.sdk.service;

import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.GenderTypeQueryVO;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.social.sdk.domain.talk.*;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.sdk.platform.AliAPI;
import com.socialuni.social.sdk.utils.GenderUtil;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.talk.*;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocialTalkService {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    SocialTalkDeleteDomain socialTalkDeleteDomain;
    @Resource
    SocialTalkDetailQueryDomain socialTalkDetailQueryDomain;
    @Resource
    SocialHomeTalkQueryDomain socialHomeTalkQueryDomain;
    @Resource
    SocialUserTalkQueryDomain socialUserTalkQueryDomain;

    public void queryParamsFormatAndCheck(SocialHomeTabTalkQueryQO queryQO, UserDO mineUser) {
        String queryGenderType = queryQO.getGender();
        //校验gender类型
        GenderTypeQueryVO genderTypeQueryVO = GenderUtil.checkQueryGenderType(queryGenderType);

        //获取genderType对应的userGender和talkGender
        //无限制，所有人都可以查询，女生和男生发布的。
        String talkVisibleGender = genderTypeQueryVO.getTalkVisibleGender();

        //校验talk可见类型是否与appgender类型一致，还有与usergender类型一致
        GenderUtil.checkTalkAndUserGender(talkVisibleGender, mineUser);

        queryQO.setUserGender(genderTypeQueryVO.getUserGender());
        queryQO.setTalkVisibleGender(talkVisibleGender);
    }

    //查询非关注tab的动态列表
    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        UserDO mineUser = SocialUserUtil.getMineUser();

        SocialHomeTabTalkQueryQO queryQO = new SocialHomeTabTalkQueryQO();
        queryQO.setTalkIds(new ArrayList<>());
        queryQO.setHomeTabType(TalkTabType.home_type);
        //如果经纬度为空
        RectangleVO rectangleVO = AliAPI.getRectangle();
        if (rectangleVO != null) {
            queryQO.setLon(rectangleVO.getLon());
            queryQO.setLat(rectangleVO.getLat());
        }
        queryQO.setMinAge(SocialAppConfig.homeTalkQueryMinAge);
        queryQO.setMaxAge(SocialAppConfig.homeTalkQueryMaxAge);
        queryQO.setGender(GenderType.all);
        this.queryParamsFormatAndCheck(queryQO, mineUser);

        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(queryQO, mineUser);
        return new ResultRO<>(socialTalkROs);
    }

    //查询非关注tab的动态列表
    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryQO) {
        //获取当前用户
        UserDO mineUser = SocialUserUtil.getMineUser();

        this.queryParamsFormatAndCheck(queryQO, mineUser);
        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(queryQO, mineUser);
        return new ResultRO<>(socialTalkROs);
    }

    public ResultRO<Void> postTalk(SocialTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialTalkPostDomain.postTalk(mineUser, talkPostQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> deleteTalk(SocialTalkDeleteQO talkDeleteQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialTalkDeleteDomain.deleteTalk(mineUser, talkDeleteQO);
        return new ResultRO<>();
    }

    public ResultRO<SocialTalkRO> queryTalkDetail(SocialTalkIdQO socialTalkIdQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        SocialTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);
        return ResultRO.success(socialTalkRO);
    }

    public ResultRO<List<SocialTalkRO>> queryUserTalks(SocialUserTalkQueryQO queryQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialTalkRO> talkROS = socialUserTalkQueryDomain.queryUserTalks(queryQO, mineUser);
        return ResultRO.success(talkROS);
    }
}