package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.user.SocialuniUserExpandAPI;
import com.socialuni.sdk.logic.service.user.SocialuniUserExpandService;
import com.socialuni.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniUserExpandController implements SocialuniUserExpandAPI {
    @Resource
    SocialuniUserExpandService socialuniUserExpandService;

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUserSchool(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        return socialuniUserExpandService.editUserSchoolName(socialMineUserDetailQO);
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {
        return socialuniUserExpandService.editUserContactInfo(socialMineUserDetailQO);
    }
}
