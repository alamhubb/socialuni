package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.sdk.model.RO.user.SocialuniListUserRO;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;

public class SocialuniListUserROFactory {
    public static SocialuniListUserRO getListUserRO(SocialuniUserDo userDO) {
        //user基础信息
        SocialuniListUserRO user = new SocialuniListUserRO(SocialuniUserROFactory.getUserRO(userDO));
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        return user;
    }
}
