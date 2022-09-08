package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.model.RO.user.CenterListUserRO;
import com.socialuni.sdk.model.RO.user.CenterUserRO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.base.SocialListUserRO;

public class CenterListUserROFactory {
    public static CenterListUserRO getListUserRO(SocialListUserRO socialListUserRO, SocialuniUserDO mineUser) {
        CenterUserRO centerUserRO = CenterUserROFactory.getUserRO(socialListUserRO, mineUser);
        //user基础信息
        CenterListUserRO user = new CenterListUserRO(centerUserRO, socialListUserRO);
        return user;
    }
}
