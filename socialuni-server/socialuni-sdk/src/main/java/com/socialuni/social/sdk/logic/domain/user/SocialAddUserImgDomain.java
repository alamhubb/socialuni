package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.user.sdk.utils.SocialuniGenerateAvatarUtil;
import com.socialuni.social.sdk.logic.domain.report.SoicialuniSystemPreCheckReportDomainDOUtil;
import com.socialuni.social.user.sdk.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.user.sdk.factory.UserImgDOFactory;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.report.sdk.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    SocialuniUserRepository userApi;

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

    public SocialuniMineUserDetailRO randomUserAvatar(SocialuniUserDo mineUser) {
        String avatarUrl;
        if (mineUser.getGender().equals(GenderType.boy)) {
            avatarUrl = SocialuniGenerateAvatarUtil.getBoyAvatar();
        } else {
            avatarUrl = SocialuniGenerateAvatarUtil.getGirlAvatar();
        }
        mineUser.setAvatar(avatarUrl);

        userApi.savePut(mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }

    public SocialuniMineUserDetailRO addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDo mineUser) {

        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        mineUser.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + socialUserImgAddQO.getSrc() + "!avatar");

        userApi.savePut(mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
