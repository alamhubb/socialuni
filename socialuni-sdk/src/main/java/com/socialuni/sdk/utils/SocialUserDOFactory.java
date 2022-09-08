package com.socialuni.sdk.utils;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.constant.GenderTypeNumEnum;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SocialUserDOFactory {
    public static SocialuniUserDO newUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialuniUserDO user = newUserByPhoneLogin();

        if (StringUtils.isNotEmpty(loginQO.getNickName())) {
            user.setNickname(loginQO.getNickName());
        }
        if (StringUtils.isNotEmpty(loginQO.getAvatarUrl())) {
            user.setAvatar(loginQO.getAvatarUrl());
        }
        if (ObjectUtils.isNotEmpty(loginQO.getGender())) {
            user.setGender(GenderTypeNumEnum.getNameByValue(loginQO.getGender()));
        }
        String userBirthday = loginQO.getBirthday();
        //判断生日是否为空
        if (StringUtils.isNotEmpty(userBirthday)) {
            //不为空使用
            user.setBirthday(userBirthday);
            user.setAge(BirthdayAgeUtil.getAgeByBirth(user.getBirthday()));
        }
        if (ObjectUtils.isNotEmpty(loginQO.getCity())) {
            user.setCity(loginQO.getCity());
        }
        return user;
    }

    public static SocialuniUserDO newUserByPhoneLogin() {
        SocialuniUserDO user = new SocialuniUserDO();
        user.setNickname("未命名");
        user.setAvatar(SocialAppConfig.getUserDefaultAvatar());
        user.setGender(GenderType.girl);
        user.setAge(SocialuniConst.defaultAge);
        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(user.getAge()));
        user.setCity("北京");
        user.setType(UserType.personal);
        return user;
    }
}