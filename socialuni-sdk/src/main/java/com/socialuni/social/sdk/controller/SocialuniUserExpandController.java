package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserExpandAPI;
import com.socialuni.social.sdk.logic.service.user.SocialuniUserExpandService;
import com.socialuni.social.sdk.model.QO.user.SocialuniUserIdQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserContactInfoEditQO;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserSchoolNameEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/userExpand")
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

    @Override
    public ResultRO<SocialuniMineUserDetailRO> switchOpenUserContactInfo(SocialUserContactInfoEditQO socialuniMineUserDetailRO) {
        return null;
    }

    @Override
    public ResultRO<SocialuniMineUserDetailRO> getUserContactInfo(SocialuniUserIdQO socialuniMineUserDetailRO) {
        return null;
    }
}
