package com.socialuni.social.user.sdk.model.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;

public class SocialuniUserROFactory {
    public static SocialuniUserRO getUserRO(SocialuniUserDo userDO, SocialuniUserDo mineUser) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDO.getUnionId());
        if (mineUser == null) {
            user.setIsMine(false);
        } else {
            user.setIsMine(mineUser.getUserId().equals(userDO.getUserId()));
        }
        user.setId(userUid);
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
