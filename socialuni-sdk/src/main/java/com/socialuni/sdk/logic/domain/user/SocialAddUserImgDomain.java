package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgAdultAuditDO;
import com.socialuni.sdk.logic.domain.report.ReportDomain;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.factory.user.img.UserImgDOFactory;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.logic.service.content.SocialuniContentCheckUtil;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.dao.repository.UserImgRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.utils.ImgContentUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;
    @Resource
    UserRepository userRepository;
    @Resource
    ReportDomain reportDomain;

    public SocialuniMineUserDetailRO addUserImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {
        SocialuniContentCheckUtil.validateImg(socialUserImgAddQO, mineUser);

        SocialUserImgDO userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);

        userImgDO = userImgRepository.save(userImgDO);


        reportDomain.checkKeywordsCreateReport(userImgDO);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }

    public SocialuniMineUserDetailRO addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {

        SocialuniContentCheckUtil.validateImg(socialUserImgAddQO, mineUser);

        mineUser.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + socialUserImgAddQO.getSrc() + "!avatar");

        userRepository.save(mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
