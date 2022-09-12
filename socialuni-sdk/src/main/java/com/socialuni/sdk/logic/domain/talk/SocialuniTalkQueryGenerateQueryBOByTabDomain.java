package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.GenderTypeQueryVO;
import com.socialuni.sdk.constant.GenderTypeVO;
import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.constant.config.SocialuniAppType;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.SocialuniUserExpandRepository;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.dao.store.SocialTagRedis;
import com.socialuni.sdk.dao.store.TalkQueryStore;
import com.socialuni.sdk.logic.entity.talk.SocialFollowUserTalksQueryEntity;
import com.socialuni.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.model.DO.SocialuniCircleDOUtil;
import com.socialuni.sdk.utils.model.DO.SocialuniUserExpandDOUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.social.web.sdk.exception.SocialSystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SocialuniTalkQueryGenerateQueryBOByTabDomain {

   public SocialHomeTabTalkQueryBO GenerateQueryBOByTab(SocialuniHomeTabTalkQueryQO queryQO,SocialuniUserDO mineUser){
       SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO = new SocialHomeTabTalkQueryBO();

       String homeTabName = queryQO.getHomeTabName();
       socialHomeTabTalkQueryBO.setHomeTabName(homeTabName);
       socialHomeTabTalkQueryBO.setHasPeopleImgTalkNeedIdentity(queryQO.getHasPeopleImgTalkNeedIdentity());
       socialHomeTabTalkQueryBO.setUserHasSchoolNam(queryQO.getUserHasSchoolNam());

       if (SocialuniAppConfig.homeTabName.equals(homeTabName)) {
           socialHomeTabTalkQueryBO.setAdCode(null);
       } else if (homeTabName.equals(SocialuniAppConfig.cityTabName)) {
           //无用逻辑，仅为注释作用，city已经是null，但为了逻辑清晰
           socialHomeTabTalkQueryBO.setCircleId(null);
       } else {
           if (homeTabName.equals(TalkTabType.self_school)) {
               homeTabName = SocialuniUserExpandDOUtil.getUserSchoolNameNotNull(mineUser.getUnionId());
           }
           SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnable(homeTabName);
           socialHomeTabTalkQueryBO.setCircleId(socialuniCircleDO.getId());
       }

       return socialHomeTabTalkQueryBO;
   }
}
