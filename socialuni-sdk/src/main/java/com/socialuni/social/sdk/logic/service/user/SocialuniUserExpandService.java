package com.socialuni.social.sdk.logic.service.user;

import com.socialuni.social.sdk.logic.domain.user.SocialuniEditExpandDomain;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialuniUserExpandService {
    @Resource
    SocialuniEditExpandDomain socialuniEditExpandDomain;

    public ResultRO<SocialuniMineUserDetailRO> editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = socialuniEditExpandDomain.editUserSchoolName(socialMineUserDetailQO);

        return ResultRO.success(socialuniMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = socialuniEditExpandDomain.editUserContactInfo(socialMineUserDetailQO);

        return ResultRO.success(socialuniMineUserDetailRO);
    }
}
