package com.socialuni.social.sdk.utils;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.CommonConst;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
import org.apache.commons.lang3.StringUtils;

public class SocialUserDOFactory {
    public static UserDO newUserByProviderLogin(SocialProviderLoginQO loginQO) {
        UserDO user = new UserDO();
        user.setNickname(loginQO.getNickName());
        user.setAvatar(loginQO.getAvatarUrl());
        user.setGender(GenderTypeNumEnum.getNameByValue(loginQO.getGender()));

        String userBirthday = loginQO.getBirthday();
        //判断生日是否为空
        if (StringUtils.isEmpty(userBirthday)) {
            //为空使用系统默认
            user.setAge(CommonConst.defaultAge);
            user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(user.getAge()));
        } else {
            //不为空使用
            user.setBirthday(userBirthday);
            user.setAge(BirthdayAgeUtil.getAgeByBirth(user.getBirthday()));
        }
        user.setCity(loginQO.getCity());
        user.setType(UserType.personal);
        return user;
    }

    public static UserDO newUserByPhoneLogin() {
        UserDO user = new UserDO();
        user.setNickname("未命名");
        user.setAvatar(SocialAppConfig.getUserDefaultAvatar());
        user.setGender(GenderType.girl);
        user.setAge(CommonConst.defaultAge);
        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(user.getAge()));
        user.setCity("北京");
        user.setType(UserType.personal);
        return user;
    }
}