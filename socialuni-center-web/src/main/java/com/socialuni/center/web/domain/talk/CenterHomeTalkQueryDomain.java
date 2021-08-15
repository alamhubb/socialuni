package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.QO.SocialHomeTalkQueryQOFactory;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.exception.base.SocialException;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.GenderTypeQueryVO;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.social.sdk.domain.talk.SocialHomeTalkQueryDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.sdk.platform.AliAPI;
import com.socialuni.social.sdk.utils.GenderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CenterHomeTalkQueryDomain {
    @Resource
    private SocialHomeTalkQueryDomain socialHomeTalkQueryDomain;


    public void queryParamsFormatAndCheck(SocialHomeTabTalkQueryQO queryQO, UserDO mineUser) {
        String queryGenderType = queryQO.getGender();
        //校验gender类型
        GenderTypeQueryVO genderTypeQueryVO = GenderUtil.checkQueryGenderType(queryGenderType);

        String appGender = DevAccountUtils.getAppGenderType();

        //获取genderType对应的userGender和talkGender
        //无限制，所有人都可以查询，女生和男生发布的。
        String talkVisibleGender = genderTypeQueryVO.getTalkVisibleGender();
        String postUserGender = genderTypeQueryVO.getUserGender();

        //校验talk可见类型是否与appgender类型一致，还有与usergender类型一致
        GenderUtil.checkAppAndVisibleGender(appGender, talkVisibleGender, postUserGender, mineUser);

        queryQO.setUserGender(postUserGender);
        queryQO.setTalkVisibleGender(talkVisibleGender);
    }

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTalks() {
        //获取当前用户
        UserDO mineUser = CenterUserUtil.getMineUser();

        CenterHomeTabTalkQueryQO queryQO = new CenterHomeTabTalkQueryQO();
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
        queryQO.setGender(DevAccountUtils.getAppGenderType());

        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = SocialHomeTalkQueryQOFactory.getTalkQueryQO(queryQO);

        this.queryParamsFormatAndCheck(socialHomeTabTalkQueryQO, mineUser);

        List<SocialTalkRO> talkRos = socialHomeTalkQueryDomain.queryHomeTabTalks(socialHomeTabTalkQueryQO, mineUser);
        List<CenterTalkRO> talkROS = CenterTalkROFactory.getTalkROS(talkRos, mineUser);
        return talkROS;
    }

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTabTalks(CenterHomeTabTalkQueryQO queryQO) {
        //获取当前用户
        UserDO mineUser = CenterUserUtil.getMineUser();

        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = SocialHomeTalkQueryQOFactory.getTalkQueryQO(queryQO);

        this.queryParamsFormatAndCheck(socialHomeTabTalkQueryQO, mineUser);

        //转换为rolist
        List<SocialTalkRO> socialTalkROFactories = socialHomeTalkQueryDomain.queryHomeTabTalks(socialHomeTabTalkQueryQO, mineUser);

        List<CenterTalkRO> talkROS = CenterTalkROFactory.getTalkROS(socialTalkROFactories, mineUser);
        return talkROS;
    }
}