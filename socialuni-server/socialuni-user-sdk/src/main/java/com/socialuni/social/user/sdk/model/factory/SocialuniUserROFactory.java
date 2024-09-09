package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import java.util.List;
import java.util.stream.Collectors;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getUserRO(SocialuniUserDo userDO) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserShowRO();
        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDO.getUnionId());

        user.setId(userUid);
        user.setAge(userDO.getAge());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }
}
