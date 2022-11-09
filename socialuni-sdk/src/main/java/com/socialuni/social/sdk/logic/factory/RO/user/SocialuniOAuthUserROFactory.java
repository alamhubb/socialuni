package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.sdk.model.RO.SocialOAuthUserRO;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.utils.BirthdayAgeUtil;

public class SocialuniOAuthUserROFactory {

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
