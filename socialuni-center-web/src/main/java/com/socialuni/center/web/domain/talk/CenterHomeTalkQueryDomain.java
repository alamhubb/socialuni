package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.QO.SocialHomeTalkQueryQOFactory;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.social.sdk.domain.talk.SocialHomeTalkQueryDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.sdk.platform.MapUtil;
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

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTalks() {
        //获取当前用户
        CenterHomeTabTalkQueryQO queryQO = new CenterHomeTabTalkQueryQO();
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
        queryQO.setGender(DevAccountUtils.getAppGenderType());

        return this.queryHomeTabTalks(queryQO);
    }

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTabTalks(CenterHomeTabTalkQueryQO queryQO) {
        //获取当前用户
        UserDO mineUser = CenterUserUtil.getMineUser();

        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = SocialHomeTalkQueryQOFactory.getTalkQueryQO(queryQO);

        //校验talk可见类型是否与appgender类型一致，还有与usergender类型一致
//        GenderUtil.checkAppAndVisibleGender(appGender, postUserGender, talkVisibleGender, mineUser);

        //主要是校验appgender,只允许同性别用户使用，不同性别则要保证同性别
        if (mineUser != null) {
            String appGender = DevAccountUtils.getAppGenderType();
            String mineUserGender = mineUser.getGender();
            //app性别为女生，且用户不为女生提示错误
            if (appGender.equals(GenderType.girl) && !mineUserGender.equals(GenderType.girl)) {
                throw new SocialSystemException("此软件为女生专属应用");
            } else if (appGender.equals(GenderType.boy) && !mineUserGender.equals(GenderType.boy)) {
                throw new SocialSystemException("此软件为男生专属应用");
            }
        }

        //转换为rolist
        List<SocialTalkRO> socialTalkROFactories = socialHomeTalkQueryDomain.queryHomeTabTalks(socialHomeTabTalkQueryQO, mineUser, DevAccountUtils.getDevId());

        List<CenterTalkRO> talkROS = CenterTalkROFactory.getTalkROS(socialTalkROFactories, mineUser);
        return talkROS;
    }
}