package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.sdk.factory.SocialTalkROFactory;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.model.RectangleVO;
import com.socialuni.sdk.platform.AliAPI;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTalkQueryQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialHomeTalkQueryDomain {
    @Resource
    private SocialHomeTalkQueryEntity socialHomeTalkQueryEntity;
    @Resource
    private SocialFollowUserTalksQueryEntity socialFollowUserTalksQueryEntity;

    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTalks(UserDO mineUser) {
        SocialHomeTalkQueryQO queryQO = new SocialHomeTalkQueryQO();
        queryQO.setTalkIds(new ArrayList<>());
        queryQO.setTabType(TalkTabType.home_type);
        //如果经纬度为空
        RectangleVO rectangleVO = AliAPI.getRectangle();
        if (rectangleVO != null) {
            queryQO.setLon(rectangleVO.getLon());
            queryQO.setLat(rectangleVO.getLat());
        }
        queryQO.setMinAge(SocialAppConfig.homeTalkQueryMinAge);
        queryQO.setMaxAge(SocialAppConfig.homeTalkQueryMaxAge);
        List<SocialTalkRO> talkRos = this.queryHomeTabTalks(queryQO, mineUser);
        return new ResultRO<>(talkRos);
    }

    //查询非关注tab的动态列表
    public List<SocialTalkRO> queryHomeTabTalks(SocialHomeTalkQueryQO queryQO, UserDO mineUser) {
        String tabType = queryQO.getTabType();
        if (!TalkTabType.values.contains(tabType)) {
            throw new SocialParamsException("错误的tab类型");
        }
        //得到数据库talk
        List<TalkDO> talkDOS;
        //为关注，查询关注
        if ((TalkTabType.follow_type.equals(tabType))) {
            talkDOS = socialFollowUserTalksQueryEntity.queryUserFollowTalks(queryQO.getTalkIds(), mineUser);
        } else {
            talkDOS = socialHomeTalkQueryEntity.queryHomeTalks(queryQO, mineUser);
        }
        //转换为rolist
        List<SocialTalkRO> socialTalkROs = SocialTalkROFactory.newHomeTalkROs(mineUser, talkDOS, queryQO);
        return socialTalkROs;
    }
}
