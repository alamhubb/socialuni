package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.sdk.logic.domain.report.SoicialuniSystemPreCheckReportDomainDOUtil;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.factory.user.img.UserImgDOFactory;
import com.socialuni.social.user.sdk.utils.content.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.user.sdk.model.RO.SocialuniMineUserDetailRO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserImgRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    SocialuniUserImgRepository userImgRepository;

    public SocialuniMineUserDetailRO addUserImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDo mineUser) {
        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        SocialuniUserImgDo userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);

        userImgDO = userImgRepository.savePut(userImgDO);

        SoicialuniSystemPreCheckReportDomainDOUtil.systemPreCheckReport(userImgDO);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
