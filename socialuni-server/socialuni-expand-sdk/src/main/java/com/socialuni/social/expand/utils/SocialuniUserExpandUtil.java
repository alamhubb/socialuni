package com.socialuni.social.expand.utils;

import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniGetUserContactRecordDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.expand.model.SocialuniUserExpandDetailRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SocialuniUserExpandUtil {

    public static SocialuniUserExpandDetailRO getMineUserExpandDetail() {
        Integer userUnionId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);

        SocialuniUserExpandDetailRO userDetailVO = new SocialuniUserExpandDetailRO();

        userDetailVO.setSchoolName(socialuniUserExpandDo.getSchoolName());

        return userDetailVO;
    }

    public static SocialuniUserExpandDetailRO getUserExpandDetailRO(SocialuniUserDo userDO) {
        Integer userUnionId = userDO.getUnionId();

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);

        SocialuniUserExpandDetailRO userDetailVO = new SocialuniUserExpandDetailRO();

        userDetailVO.setSchoolName(socialuniUserExpandDo.getSchoolName());

        return userDetailVO;
    }
}
