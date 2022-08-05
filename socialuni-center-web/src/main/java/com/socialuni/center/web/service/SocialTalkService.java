package com.socialuni.center.web.service;

import com.socialuni.center.web.constant.TalkTabType;
import com.socialuni.center.web.domain.talk.*;
import com.socialuni.center.web.model.RectangleVO;
import com.socialuni.center.web.platform.MapUtil;
import com.socialuni.social.constant.GenderType;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.model.QO.community.talk.*;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
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

    //查询非关注tab的动态列表
    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialHomeTabTalkQueryQO queryQO = new SocialHomeTabTalkQueryQO();
        queryQO.setTalkIds(new ArrayList<>());
        queryQO.setHomeTabType(TalkTabType.home_type);
        //如果经纬度为空
        RectangleVO rectangleVO = MapUtil.getRectangle();
        if (rectangleVO != null) {
            queryQO.setLon(rectangleVO.getLon());
            queryQO.setLat(rectangleVO.getLat());
        }
        queryQO.setMinAge(SocialAppConfig.homeTalkQueryMinAge);
        queryQO.setMaxAge(SocialAppConfig.homeTalkQueryMaxAge);
        queryQO.setGender(GenderType.all);

        //转换为rolist
        return this.queryHomeTabTalks(queryQO);
    }

    //查询非关注tab的动态列表
    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryQO) {
        //获取当前用户
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(queryQO, mineUser);
        return new ResultRO<>(socialTalkROs);
    }

    public ResultRO<Void> postTalk(SocialTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        socialTalkPostDomain.postTalk(talkPostQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> deleteTalk(SocialTalkDeleteQO talkDeleteQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        socialTalkDeleteDomain.deleteTalk(mineUser, talkDeleteQO);
        return new ResultRO<>();
    }

    public ResultRO<SocialTalkRO> queryTalkDetail(SocialTalkIdQO socialTalkIdQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        SocialTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);
        return ResultRO.success(socialTalkRO);
    }

    public ResultRO<List<SocialTalkRO>> queryUserTalks(SocialUserTalkQueryQO queryQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        List<SocialTalkRO> talkROS = socialUserTalkQueryDomain.queryUserTalks(queryQO, mineUser);
        return ResultRO.success(talkROS);
    }
}