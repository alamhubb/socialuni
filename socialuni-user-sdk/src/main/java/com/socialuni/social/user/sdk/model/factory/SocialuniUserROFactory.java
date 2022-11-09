package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getMineUser() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return SocialuniUserROFactory.getUserRO(mineUser);
    }

    public static SocialuniUserRO getUserRO(SocialuniUserDo userDO) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDO.getUnionId());
        user.setId(userUid);
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
