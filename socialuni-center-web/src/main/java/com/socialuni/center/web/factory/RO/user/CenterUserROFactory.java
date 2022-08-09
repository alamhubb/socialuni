package com.socialuni.center.web.factory.RO.user;

import com.socialuni.center.web.model.RO.user.CenterUserRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialUserRO;

public class CenterUserROFactory {

    public static CenterUserRO getUserRO(SocialUserRO socialUserRO, SocialUserDO mineUser) {
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
