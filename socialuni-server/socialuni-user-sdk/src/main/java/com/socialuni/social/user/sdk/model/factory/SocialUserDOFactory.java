package com.socialuni.social.user.sdk.model.factory;

import com.qingchi.qing.common.exception.base.QingException;
import com.socialuni.social.common.sdk.utils.SocialuniGenerateAvatarUtil;
import com.socialuni.social.content.utils.BirthdayAgeUtil;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.user.sdk.utils.GenerateNicknameUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Slf4j
public class SocialUserDOFactory {
    public static SocialuniUserDo newUserByProviderLogin(SocialProviderLoginQO loginQO, Long unionId) {
        SocialuniUserDo user = newUserByPhoneLogin(unionId);

        String nickname = loginQO.getNickName();

        //不为0 不为空，则设置性别
        if (ObjectUtils.isNotEmpty(loginQO.getGender()) && (Arrays.asList(GenderTypeNumEnum.male.getValue(), GenderTypeNumEnum.female.getValue()).contains(loginQO.getGender()))) {
            user.setGender(GenderTypeNumEnum.getNameByValue(loginQO.getGender()));
        }

        if (user.getGender().equals(GenderType.boy)) {
            user.setNickname(GenerateNicknameUtil.getBoyName());
            user.setAvatar(SocialuniGenerateAvatarUtil.getBoyAvatar());
        } else {
            user.setNickname(GenerateNicknameUtil.getGirlName());
            user.setAvatar(SocialuniGenerateAvatarUtil.getGirlAvatar());
        }


        /*if (StringUtils.isNotEmpty(nickname)) {
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
        }*/
        /*if (StringUtils.isNotEmpty(loginQO.getAvatarUrl())) {
            user.setAvatar(loginQO.getAvatarUrl());
        }*/

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
            try {
                SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(loginQO.getCity());
                user.setCity(StringUtils.substring(loginQO.getCity(), 0, 10));
            } catch (QingException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static SocialuniUserDo newUserByPhoneLogin(Long unionId) {
        return newUserByNickname(GenerateNicknameUtil.getGirlName(), unionId);
    }

    public static SocialuniUserDo newUserByNickname(String nickname, Long unionId) {
        if (StringUtils.isEmpty(nickname)) {
            nickname = GenerateNicknameUtil.getGirlName();
        }
        nickname = StringUtils.substring(nickname, 0, 6);


        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(unionId);

        Integer devId = socialuniUnionIdDo.getFromDevId();

        SocialuniUserDo user = new SocialuniUserDo(devId, unionId);
        user.setNickname(nickname);
        user.setAvatar(SocialuniGenerateAvatarUtil.getGirlAvatar());
        user.setGender(GenderType.girl);
        user.setContentType(SocialuniContentType.user);
        user.setAge(SocialuniConst.defaultAge);
        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(user.getAge()));
        user.setCity("北京");
        user.setType(UserType.personal);
        return user;
    }
}