package com.socialuni.center.web.factory.user.base;

import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialUserRO;

public class SocialUserROFactory {

    public static SocialUserRO getUserRO(SocialUserDO userDO) {
        //user基础信息
        SocialUserRO user = new SocialUserRO(SocialContentIdROFactory.getContentIdRO(userDO));
        user.setId(userDO.getId());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
