package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserRO;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getUserRO(SocialuniUserModel userDO) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDO.getUnionId());
        user.setId(userUid);
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
