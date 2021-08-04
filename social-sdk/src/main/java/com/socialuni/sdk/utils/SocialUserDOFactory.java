package com.socialuni.sdk.utils;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.CommonConst;
import com.socialuni.sdk.constant.GenderType;
import com.socialuni.sdk.constant.GenderTypeNumEnum;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;

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