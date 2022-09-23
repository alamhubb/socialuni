package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.logic.domain.user.SocialuniEditExpandDomain;
import com.socialuni.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.sdk.model.RO.user.*;
import com.socialuni.social.common.model.ResultRO;
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
