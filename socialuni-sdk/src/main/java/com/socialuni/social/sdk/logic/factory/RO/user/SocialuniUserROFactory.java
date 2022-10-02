package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserRO;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getUserRO(SocialuniUserDO userDO) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = SocialuniUnionIdUtil.getUuidByUnionIdNotNull(userDO.getUnionId());
        user.setId(userUid);
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}