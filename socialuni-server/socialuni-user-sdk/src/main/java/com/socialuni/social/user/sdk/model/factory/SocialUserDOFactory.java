package com.socialuni.social.user.sdk.model.factory;

import cn.hutool.core.util.ReUtil;
import com.socialuni.social.common.api.exception.base.SocialException;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.user.sdk.constant.UserType;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.content.SocialuniTextContentUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SocialUserDOFactory {
    public static SocialuniUserDo newUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialuniUserDo user = newUserByPhoneLogin();

        String nickname = loginQO.getNickName();

        if (StringUtils.isNotEmpty(nickname)) {
            //校验是否包含违规内容，包含则不设置
            try {
                SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(nickname);
                boolean containsSpecialChars = ReUtil.contains("[^a-zA-Z0-9\\u4E00-\\u9FA5]", nickname);
                //不包含特殊字符
                if (!containsSpecialChars) {
                    user.setNickname(loginQO.getNickName());
                }
            } catch (SocialException e) {
                e.printStackTrace();
            }
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
            int age = BirthdayAgeUtil.getAgeByBirth(user.getBirthday());
            if (age > 50 || age < 18) {
                //不允许的年龄，则不设置
            } else {
                user.setAge(age);
                //不为空使用
                user.setBirthday(userBirthday);
            }
        }
        if (ObjectUtils.isNotEmpty(loginQO.getCity())) {
            user.setCity(StringUtils.substring(loginQO.getCity(), 0, 10));
        }
        return user;
    }

    public static SocialuniUserDo newUserByPhoneLogin() {
        Integer userUnionId = SocialuniUnionIdFacede.createUserUnionId();
        SocialuniUserDo user = new SocialuniUserDo(userUnionId);
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