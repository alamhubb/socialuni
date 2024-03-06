package com.socialuni.social.user.sdk.factory;

import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.report.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.model.RO.SocialOAuthUserRO;

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
