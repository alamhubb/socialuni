package com.socialuni.center.web.factory.RO.user;

import com.socialuni.center.web.model.RO.user.CenterListUserRO;
import com.socialuni.center.web.model.RO.user.CenterUserRO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialListUserRO;

public class CenterListUserROFactory {
    public static CenterListUserRO getListUserRO(SocialListUserRO socialListUserRO, SocialUserDO mineUser) {
        CenterUserRO centerUserRO = CenterUserROFactory.getUserRO(socialListUserRO, mineUser);
        //user基础信息
        CenterListUserRO user = new CenterListUserRO(centerUserRO, socialListUserRO);
        return user;
    }
}
