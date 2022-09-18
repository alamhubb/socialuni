package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.sdk.dao.repository.SocialuniUserImgRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.logic.domain.report.SoicialuniReportDomainDOUtil;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.factory.user.img.UserImgDOFactory;
import com.socialuni.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    SocialuniUserImgRepository userImgRepository;
    @Resource
    UserRepository userRepository;
    @Resource
    SoicialuniReportDomainDOUtil soicialuniReportDomain;

    public SocialuniMineUserDetailRO addUserImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        SocialuniUserImgDO userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);

        userImgDO = userImgRepository.save(userImgDO);

        soicialuniReportDomain.systemPreCheckReport(userImgDO);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }

    public SocialuniMineUserDetailRO addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {

        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        mineUser.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + socialUserImgAddQO.getSrc() + "!avatar");

        userRepository.save(mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
