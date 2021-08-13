package com.socialuni.social.sdk.domain.talk;

import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.social.sdk.entity.talk.SocialFollowUserTalksQueryEntity;
import com.socialuni.social.sdk.entity.talk.SocialHomeTalkQueryEntity;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.social.sdk.factory.SocialTalkROFactory;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.sdk.platform.AliAPI;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SocialHomeTalkQueryDomain {
    @Resource
    private SocialHomeTalkQueryEntity socialHomeTalkQueryEntity;
    @Resource
    private SocialFollowUserTalksQueryEntity socialFollowUserTalksQueryEntity;

    //查询非关注tab的动态列表
    public List<SocialTalkRO> queryHomeTalks(UserDO mineUser) {
        SocialHomeTabTalkQueryQO queryQO = new SocialHomeTabTalkQueryQO();
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
        return talkRos;
    }

    //查询非关注tab的动态列表
    public List<SocialTalkRO> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryQO, UserDO mineUser) {
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
