package com.socialuni.social.sdk.im.factory;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.api.model.SocialuniNotifyUserRO;

public class SocialuniNotifyUserROFactory {
    public static SocialuniNotifyUserRO getNotifyUser(SocialuniUserDo socialuniUserDo) {
        SocialuniNotifyUserRO notifyUserVO = SocialuniNotifyUserRO.builder()
                .nickname(socialuniUserDo.getNickname())
                .avatar(socialuniUserDo.getAvatar())
                .gender(socialuniUserDo.getGender())
                .age(socialuniUserDo.getAge())
                .build();
        return notifyUserVO;
    }
}
