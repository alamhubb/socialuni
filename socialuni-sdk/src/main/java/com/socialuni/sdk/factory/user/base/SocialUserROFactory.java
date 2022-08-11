package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;

public class SocialUserROFactory {

    public static SocialUserRO getUserRO(SocialUserDO userDO) {
        //user基础信息
        SocialUserRO user = new SocialUserRO(SocialContentIdROFactory.getContentIdRO(userDO));
        user.setId(userDO.getUnionId());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
