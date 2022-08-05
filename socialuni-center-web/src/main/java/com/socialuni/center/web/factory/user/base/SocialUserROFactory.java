package com.socialuni.center.web.factory.user.base;

import com.socialuni.social.model.model.RO.user.base.SocialUserRO;
import com.socialuni.social.entity.model.DO.user.UserDO;

public class SocialUserROFactory {

    public static SocialUserRO getUserRO(UserDO userDO) {
        //user基础信息
        SocialUserRO user = new SocialUserRO();
        user.setId(userDO.getId());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
