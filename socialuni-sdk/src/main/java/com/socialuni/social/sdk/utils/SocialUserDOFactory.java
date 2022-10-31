package com.socialuni.social.sdk.utils;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.constant.SocialuniConst;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SocialUserDOFactory {
    public static SocialuniUserModel newUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialuniUserModel user = newUserByPhoneLogin();

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

    public static SocialuniUserModel newUserByPhoneLogin() {
        Integer userUnionId = SocialuniUnionIdFacede.createUserUnionId();
        SocialuniUserModel user = new SocialuniUserModel();
        user.setUserId(userUnionId);
        user.setUnionId(userUnionId);
        user.setNickname("未命名");
        user.setAvatar(SocialuniSystemConst.getUserDefaultAvatar());
        user.setGender(GenderType.girl);
        user.setContentType(SocialuniContentType.user);
        user.setAge(SocialuniConst.defaultAge);
        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(user.getAge()));
        user.setCity("北京");
        user.setType(UserType.personal);
        return user;
    }
}