package com.socialuni.social.userImg.logic.domain;


import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.dao.repository.SocialuniUserImgRepository;
import com.socialuni.social.userImg.factory.UserImgDOFactory;
import com.socialuni.social.common.sdk.utils.SocialuniGenerateAvatarUtil;
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

    public void addUserImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDo mineUser) {
        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        SocialuniUserImgDo userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);

        userImgDO = userImgRepository.savePut(userImgDO);

//        SoicialuniSystemPreCheckReportDomainDOUtil.systemPreCheckReport(userImgDO);


//        return socialMineUserDetailRO;
//        return null;
    }
}
