package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.sdk.constant.GenderTypeNumEnum;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;

public class SocialOAuthUserROFactory {

    public static SocialOAuthUserRO getSocialOAuthUserRO(SocialuniMineUserDetailRO mineUser) {
        SocialOAuthUserRO socialUserRO = new SocialOAuthUserRO();
        socialUserRO.setOpenId(mineUser.getId().toString());
        socialUserRO.setUnionId(mineUser.getId().toString());
        socialUserRO.setNickName(mineUser.getNickname());
        socialUserRO.setAvatarUrl(mineUser.getAvatar());
        socialUserRO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
        socialUserRO.setYear(BirthdayAgeUtil.getYearStrByAge(mineUser.getAge()));
        socialUserRO.setCity(mineUser.getCity());
        /*if (showPhoneNum) {
            socialUserRO.setPhoneNum(mineUser.getPhoneNum());
        }*/
        return socialUserRO;
    }

}
