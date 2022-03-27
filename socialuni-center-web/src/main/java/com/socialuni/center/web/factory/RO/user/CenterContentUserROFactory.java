package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterContentUserRO;
import com.socialuni.api.model.RO.user.CenterListUserRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialContentUserRO;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;

public class CenterContentUserROFactory {
    public static CenterContentUserRO getContentUserRO(SocialContentUserRO userRO, UserDO mineUser) {
        CenterListUserRO centerUserRO = CenterListUserROFactory.getListUserRO(userRO, mineUser);
        //user基础信息
        CenterContentUserRO user = new CenterContentUserRO(centerUserRO, userRO);
        user.setAge(BirthdayAgeUtil.replaceAgeBetween10to18StrTo18(user.getAge()));
        return user;
    }

}
