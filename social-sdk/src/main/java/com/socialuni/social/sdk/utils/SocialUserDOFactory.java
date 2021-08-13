package com.socialuni.social.sdk.utils;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.CommonConst;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;

public class SocialUserDOFactory {
    public static UserDO newUserByProviderLogin(SocialProviderLoginQO loginQO) {
        UserDO user = new UserDO();
        user.setNickname(loginQO.getNickName());
        user.setAvatar(loginQO.getAvatarUrl());
        user.setGender(GenderTypeNumEnum.getNameByValue(loginQO.getGender()));
        user.setBirthday(loginQO.getBirthday());
        user.setAge(BirthdayAgeUtil.getAgeByBirth(loginQO.getBirthday()));
        user.setCity(loginQO.getCity());
        user.setType(UserType.personal);
        return user;
    }

    public static UserDO newUserByPhoneLogin() {
        UserDO user = new UserDO();
        user.setNickname("未命名");
        user.setAvatar(SocialAppConfig.userDefaultAvatar);
        user.setGender(GenderType.girl);
        user.setAge(CommonConst.defaultAge);
        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(user.getAge()));
        user.setCity("北京");
        user.setType(UserType.personal);
        return user;
    }
}