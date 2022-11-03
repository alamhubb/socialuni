package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.user.edit.SocialUserEditQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.utils.GenderUtil;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.social.common.sdk.exception.exception.SocialBusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialEditUserDomain {
    @Resource
    UserApi userApi;

    public SocialuniMineUserDetailRO editUser(SocialUserEditQO socialUserEditQO, SocialuniUserModel mineUser) {
        //昵称
        String nickname = socialUserEditQO.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            throw new SocialBusinessException("昵称不能为空");
        }
        if (nickname.length() > 6) {
            throw new SocialBusinessException("昵称长度不能大于6");
        } else {
            String oldNickname = mineUser.getNickname();
            //新旧昵称不一样，则更新
            if (!nickname.equals(oldNickname)) {
                SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(nickname);
                mineUser.setNickname(StringUtils.substring(nickname, 0, 6));
            }
        }

        String editGender = socialUserEditQO.getGender();

        GenderUtil.checkUserEditGenderType(editGender);

        mineUser.setGender(editGender);

        //生日，年龄
        String birthday = socialUserEditQO.getBirthday();
        if (StringUtils.isNotEmpty(birthday)) {
            mineUser.setBirthday(birthday);
            int age = BirthdayAgeUtil.getAgeByBirth(birthday);
            if (age < 18) {
                throw new SocialBusinessException("年龄不能小于18岁");
            }
            mineUser.setAge(BirthdayAgeUtil.getAgeByBirth(birthday));
        }

        String userCity = socialUserEditQO.getCity();
        //保存地区名
        if (StringUtils.isNotEmpty(userCity)) {
            if (userCity.length() > 10) {
                throw new SocialBusinessException("市县区名称长度不能大于10");
            } else {
                String oldCity = mineUser.getCity();
                //新旧昵称不一样，则更新
                if (!userCity.equals(oldCity)) {
                    SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(userCity);
                    mineUser.setCity(userCity);
                }
            }
        }
        mineUser.setUpdateTime(new Date());

        mineUser = userApi.savePut(mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
        return socialMineUserDetailRO;
    }
}
