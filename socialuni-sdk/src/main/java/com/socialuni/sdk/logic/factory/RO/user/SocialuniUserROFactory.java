package com.socialuni.sdk.logic.factory.RO.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniUserRO;
import com.socialuni.sdk.utils.UnionIdUtil;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getUserRO(SocialuniUserDO userDO) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = UnionIdUtil.getUuidByUnionIdNotNull(userDO.getUnionId());
        user.setId(userUid);
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
