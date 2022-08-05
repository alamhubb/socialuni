package com.socialuni.center.web.factory.user;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.community.SocialuniContentIdRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;

public class SocialuniContentIdROFactory {

    public static SocialuniContentIdRO get(UserDO userDO) {
        //user基础信息
        SocialUserRO user = new SocialUserRO();
        user.setId(userDO.getId());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
