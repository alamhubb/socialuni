package com.socialuni.center.web.factory.user;

import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.entity.model.DO.UniContentBaseDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.community.UniContentIdRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserRO;

public class UniContentIdROFactory {

    public static UniContentIdRO get(UniContentBaseDO contentBaseDO) {
        //user基础信息
        UniContentIdRO contentIdRO = new UniContentIdRO();

        String contentType = contentBaseDO.getContentType();
        Integer id = contentBaseDO.getId();

        UniContentUnionIdDO uniContentUnionIdDO = UnionIdDbUtil.get

        contentIdRO.setc

        user.setId(userDO.getId());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(userDO.getGender());
        return user;
    }

}
