package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.sdk.config.SocialuniAppConfig;
import com.socialuni.social.sdk.config.SocialuniAppConfigBO;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.social.sdk.dao.utils.SocialuniCircleDOUtil;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.social.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class SocialuniTalkQueryGenerateQueryBOByTabDomain {
    public SocialHomeTabTalkQueryBO generateQueryBOByTab(SocialuniHomeTabTalkQueryQO queryQO, SocialuniUserDo mineUser) {
        SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO = new SocialHomeTabTalkQueryBO();

        //如果后台配置了，则以后台为主，如果后台为null则看前台传值，
        SocialuniAppConfigBO socialuniAppConfigBO = SocialuniAppConfig.getAppConfig();

        //兼容旧版本
        socialHomeTabTalkQueryBO.setTalkIds(queryQO.getTalkIds());
        String homeTabName = queryQO.getHomeTabName();
        if (StringUtils.isEmpty(homeTabName)) {
            String homeTabType = queryQO.getHomeTabType();
            if (TalkTabType.home_type.equals(homeTabType)) {
                homeTabName = socialuniAppConfigBO.getHomeTabName();
            } else if (TalkTabType.city_type.equals(homeTabType)) {
                homeTabName = socialuniAppConfigBO.getCityTabName();
            } else if (TalkTabType.follow_type.equals(homeTabType)) {
                homeTabName = socialuniAppConfigBO.getFollowTabName();
            } else if (TalkTabType.circle_type.equals(homeTabType)) {
                homeTabName = queryQO.getCircleName();
            }
        }
        socialHomeTabTalkQueryBO.setHomeTabName(homeTabName);

        //优先使用后台配置
//        socialHomeTabTalkQueryBO.setHasPeopleImgNeedIdentity(socialuniAppConfigBO.getNeedAdultCanPostPeoplePhoto());
        socialHomeTabTalkQueryBO.setUserHasSchoolNam(socialuniAppConfigBO.getMustSetSchoolCanPost());

        if (socialuniAppConfigBO.getHomeTabName().equals(homeTabName)) {
            socialHomeTabTalkQueryBO.setAdCode(null);
        } else if (Arrays.asList(socialuniAppConfigBO.getCityTabName(), socialuniAppConfigBO.getFollowTabName()).contains(homeTabName)) {
            //无用逻辑，仅为注释作用，city已经是null，但为了逻辑清晰，城市和关注无需处理
            socialHomeTabTalkQueryBO.setCircleId(null);
        } else {
            this.generateQueryBOByNotHomeCityOthers(mineUser, socialHomeTabTalkQueryBO);
        }
        return socialHomeTabTalkQueryBO;
    }

    public void generateQueryBOByNotHomeCityOthers(SocialuniUserDo mineUser, SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO) {
        String homeTabName = socialHomeTabTalkQueryBO.getHomeTabName();
        if (homeTabName.equals(SocialuniAppConfig.getAppConfig().getSelfSchoolTabName())) {
            homeTabName = SocialuniUserExpandDOUtil.getUserSchoolNameNotNull(mineUser.getUnionId());
        }
        SocialuniCircleDO SocialuniCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(homeTabName);
        socialHomeTabTalkQueryBO.setCircleId(SocialuniCircleDO.getId());
    }
}
