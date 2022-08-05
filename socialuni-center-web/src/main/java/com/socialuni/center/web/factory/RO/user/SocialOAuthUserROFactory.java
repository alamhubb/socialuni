package com.socialuni.center.web.factory.RO.user;

import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.model.RO.SocialOAuthUserRO;
import com.socialuni.center.web.constant.GenderTypeNumEnum;
import com.socialuni.center.web.utils.common.BirthdayAgeUtil;

public class SocialOAuthUserROFactory {

    public static SocialOAuthUserRO getSocialOAuthUserRO(CenterMineUserDetailRO mineUser) {
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
