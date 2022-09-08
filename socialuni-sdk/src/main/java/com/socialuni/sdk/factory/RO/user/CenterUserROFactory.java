package com.socialuni.sdk.factory.RO.user;

import com.socialuni.sdk.model.RO.user.CenterUserRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;

public class CenterUserROFactory {

    public static CenterUserRO getUserRO(SocialUserRO socialUserRO, SocialuniUserDO mineUser) {
        //user基础信息
        CenterUserRO user = new CenterUserRO(socialUserRO);
        String uid = UnionIdDbUtil.getUidByUnionIdNotNull(socialUserRO.getId());
        user.setId(uid);
        /*user.setAvatar(socialUserRO.getAvatar());
        user.setGender(socialUserRO.getGender());
        user.setNickname(socialUserRO.getNickname());*/
        return user;
    }

}
