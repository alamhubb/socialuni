package com.socialuni.sdk.service;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.domain.talk.SocialHomeTalksQueryDomain;
import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.factory.TalkROFactory;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.model.RectangleVO;
import com.socialuni.sdk.platform.AliAPI;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkHomeQueryQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocialTalkQueryService {
    @Resource
    private SocialHomeTalksQueryDomain socialHomeTalksQueryDomain;

    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        SocialTalkHomeQueryQO queryVO = new SocialTalkHomeQueryQO();
        queryVO.setTalkIds(new ArrayList<>());
        queryVO.setTabType(TalkTabType.home_type);
        //如果经纬度为空
        RectangleVO rectangleVO = AliAPI.getRectangle();
        if (rectangleVO != null) {
            queryVO.setLon(rectangleVO.getLon());
            queryVO.setLat(rectangleVO.getLat());
        }
        queryVO.setMinAge(SocialAppConfig.homeTalkQueryMinAge);
        queryVO.setMaxAge(SocialAppConfig.homeTalkQueryMaxAge);
        ResultRO<List<SocialTalkRO>> talkRos = this.queryTabHomeTalks(queryVO);
        return new ResultRO<>(talkRos);
    }

    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryTabHomeTalks(SocialTalkHomeQueryQO queryVO) {
        //获取当前用户
        UserDO mineUser = SocialUserUtil.getMineUser();
        //得到int的id列表
        List<Integer> talkIds = ListConvertUtil.toList(Integer::valueOf, queryVO.getTalkIds());
        //得到数据库talk
        List<TalkDO> talkDOS = socialHomeTalksQueryDomain.queryHomeTalks(talkIds, queryVO, mineUser);
        //转换为rolist
        List<SocialTalkRO> socialTalkROFactories = TalkROFactory.newHomeTalkROs(mineUser, talkDOS, queryVO);
        return new ResultRO<>(socialTalkROFactories);
    }
}