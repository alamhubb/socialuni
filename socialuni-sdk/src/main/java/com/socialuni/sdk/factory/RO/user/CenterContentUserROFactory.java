package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.model.RO.user.CenterContentUserRO;
import com.socialuni.sdk.model.RO.user.CenterListUserRO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.user.base.SocialContentUserRO;
import com.socialuni.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;

import javax.annotation.Resource;

public class CenterContentUserROFactory {
    @Resource
    private SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;


    public static CenterContentUserRO getContentUserRO(SocialContentUserRO userRO, SocialUserDO mineUser) {
        CenterListUserRO centerUserRO = CenterListUserROFactory.getListUserRO(userRO, mineUser);
        //user基础信息
        CenterContentUserRO user = new CenterContentUserRO(centerUserRO, userRO);
        user.setAge(BirthdayAgeUtil.replaceAgeBetween10to18StrTo18(user.getAge()));
        return user;
    }

}
