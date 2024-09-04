package com.socialuni.social.expand.utils;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.expand.model.SocialuniUserExpandDetailEditRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniUserExpandUtil {

    public static SocialuniUserExpandDetailEditRO getMineUserExpandDetail() {
        Long userUnionId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);

        SocialuniUserExpandDetailEditRO userDetailVO = new SocialuniUserExpandDetailEditRO();

        userDetailVO.setSchoolName(socialuniUserExpandDo.getSchoolName());

        return userDetailVO;
    }

    public static SocialuniUserExpandDetailEditRO getUserExpandDetailRO(SocialuniUserDo userDO) {
        Long userUnionId = userDO.getUnionId();

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);

        SocialuniUserExpandDetailEditRO userDetailVO = new SocialuniUserExpandDetailEditRO();

        userDetailVO.setSchoolName(socialuniUserExpandDo.getSchoolName());

        return userDetailVO;
    }
}
