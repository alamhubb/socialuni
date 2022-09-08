package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.logic.domain.user.SocialuniEditUserSchoolNameDomain;
import com.socialuni.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.user.*;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.social.web.sdk.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialuniUserExpandService {
    @Resource
    SocialuniEditUserSchoolNameDomain socialEditUserSchoolNameDomain;

    public ResultRO<SocialuniMineUserDetailRO> editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = socialEditUserSchoolNameDomain.editUserSchoolName(socialMineUserDetailQO);

        return ResultRO.success(socialuniMineUserDetailRO);
    }
}
