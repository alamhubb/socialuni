package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterListUserRO;
import com.socialuni.api.model.RO.user.CenterUserRO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialListUserRO;

public class CenterListUserROFactory {
    public static CenterListUserRO getListUserRO(SocialListUserRO socialListUserRO, UserDO mineUser) {
        CenterUserRO centerUserRO = CenterUserROFactory.getUserRO(socialListUserRO, mineUser);
        //user基础信息
        CenterListUserRO user = new CenterListUserRO(centerUserRO, socialListUserRO);
        return user;
    }
}
