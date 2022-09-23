package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.sdk.config.SocialuniSystemConst;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.social.sdk.dao.repository.SocialuniUserImgRepository;
import com.socialuni.social.sdk.dao.repository.UserRepository;
import com.socialuni.social.sdk.logic.domain.report.SoicialuniSystemPreCheckReportDomainDOUtil;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.factory.user.img.UserImgDOFactory;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
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

    public SocialuniMineUserDetailRO addUserImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        SocialuniUserImgDO userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);

        userImgDO = userImgRepository.save(userImgDO);

        SoicialuniSystemPreCheckReportDomainDOUtil.systemPreCheckReport(userImgDO);

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
