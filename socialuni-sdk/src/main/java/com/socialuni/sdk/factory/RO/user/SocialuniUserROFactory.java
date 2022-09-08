package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.SocialuniUserRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;

public class SocialuniUserROFactory {

    public static SocialuniUserRO getUserRO(SocialuniUserDO userDO) {
        //user基础信息
        SocialuniUserRO user = new SocialuniUserRO();
        String userUid = UnionIdDbUtil.getUidByUnionIdNotNull(userDO.getUnionId());
        user.setId(userUid);
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
