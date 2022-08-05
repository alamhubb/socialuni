package com.socialuni.center.web.factory.user.base;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialListUserRO;

public class SocialListUserROFactory {
    public static SocialListUserRO getListUserRO(UserDO userDO) {
        //user基础信息
        SocialListUserRO user = new SocialListUserRO(SocialUserROFactory.getUserRO(userDO));
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        return user;
    }
}
