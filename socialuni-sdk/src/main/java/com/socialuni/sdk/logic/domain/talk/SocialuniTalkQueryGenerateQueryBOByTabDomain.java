package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniAppConfigBO;
import com.socialuni.sdk.constant.platform.UniappProviderType;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.sdk.utils.model.DO.SocialuniCircleDOUtil;
import com.socialuni.sdk.utils.model.DO.SocialuniUserExpandDOUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
public class SocialuniTalkQueryGenerateQueryBOByTabDomain {
    public SocialHomeTabTalkQueryBO generateQueryBOByTab(SocialuniHomeTabTalkQueryQO queryQO, SocialuniUserDO mineUser) {
        SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO = new SocialHomeTabTalkQueryBO();

        String homeTabName = queryQO.getHomeTabName();


        //如果后台配置了，则以后台为主，如果后台为null则看前台传值，
        SocialuniAppConfigBO socialuniAppConfigBO = SocialuniAppConfig.appConfig;
        //优先使用后台配置
//        socialHomeTabTalkQueryBO.setHasPeopleImgNeedIdentity(socialuniAppConfigBO.getNeedAdultCanPostPeoplePhoto());
        socialHomeTabTalkQueryBO.setUserHasSchoolNam(socialuniAppConfigBO.getHasSchoolCanPostTalk());

        if (SocialuniAppConfig.appConfig.getHomeTabName().equals(homeTabName)) {
            socialHomeTabTalkQueryBO.setAdCode(null);
        } else if (Arrays.asList(SocialuniAppConfig.appConfig.getCityTabName(), SocialuniAppConfig.appConfig.getFollowTabName()).contains(homeTabName)) {
            //无用逻辑，仅为注释作用，city已经是null，但为了逻辑清晰，城市和关注无需处理
            socialHomeTabTalkQueryBO.setCircleId(null);
        } else {
            this.generateQueryBOByNotHomeCityOthers(queryQO, mineUser, socialHomeTabTalkQueryBO);
        }
        return socialHomeTabTalkQueryBO;
    }

    public void generateQueryBOByNotHomeCityOthers(SocialuniHomeTabTalkQueryQO queryQO, SocialuniUserDO mineUser, SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO) {
        String homeTabName = queryQO.getHomeTabName();
        if (homeTabName.equals(SocialuniAppConfig.appConfig.getSelfSchoolTabName())) {
            homeTabName = SocialuniUserExpandDOUtil.getUserSchoolNameNotNull(mineUser.getUnionId());
        }
        SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnable(homeTabName);
        socialHomeTabTalkQueryBO.setCircleId(socialuniCircleDO.getId());
    }
}
