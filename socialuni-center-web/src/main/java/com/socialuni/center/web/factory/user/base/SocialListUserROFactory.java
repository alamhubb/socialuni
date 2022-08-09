package com.socialuni.center.web.factory.user.base;

import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialListUserRO;

public class SocialListUserROFactory {
    public static SocialListUserRO getListUserRO(SocialUserDO userDO) {
        //user基础信息
        SocialListUserRO user = new SocialListUserRO(SocialUserROFactory.getUserRO(userDO));
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        return user;
    }
}