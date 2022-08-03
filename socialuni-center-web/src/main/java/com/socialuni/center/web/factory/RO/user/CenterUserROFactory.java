package com.socialuni.center.web.factory.RO.user;

import com.socialuni.api.model.RO.user.CenterUserRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;

public class CenterUserROFactory {

    public static CenterUserRO getUserRO(SocialUserRO socialUserRO, UserDO mineUser) {
        //user基础信息
        CenterUserRO user = new CenterUserRO(socialUserRO);
        String uid = UnionIdDbUtil.createUserUid(socialUserRO.getId(), mineUser);
        user.setId(Integer.valueOf(uid));
        /*user.setAvatar(socialUserRO.getAvatar());
        user.setGender(socialUserRO.getGender());
        user.setNickname(socialUserRO.getNickname());*/
        return user;
    }

}
