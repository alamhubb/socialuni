package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterContentUserRO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;

public class SocialOAuthUserROFactory {

    public static SocialOAuthUserRO getSocialOAuthUserRO(CenterMineUserDetailRO mineUser, boolean showPhoneNum) {
        SocialOAuthUserRO socialUserRO = new SocialOAuthUserRO();
        socialUserRO.setOpenId(mineUser.getId());
        socialUserRO.setUnionId(mineUser.getId());
        socialUserRO.setNickName(mineUser.getNickname());
        socialUserRO.setAvatarUrl(mineUser.getAvatar());
        socialUserRO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
        socialUserRO.setYear(BirthdayAgeUtil.getYearBirthDateByAge(mineUser.getAge()));
        socialUserRO.setCity(mineUser.getCity());
        if (showPhoneNum) {
            socialUserRO.setPhoneNum(mineUser.getPhoneNum());
        }
        return socialUserRO;
    }

}
