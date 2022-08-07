package com.socialuni.center.web.factory.user.base;

import com.socialuni.center.web.model.DO.SocialCommonBaseDO;
import com.socialuni.center.web.model.RO.community.SocialContentIdRO;

public class SocialContentIdROFactory {
    public static SocialContentIdRO getContentIdRO(SocialCommonBaseDO contentIdCO) {
        //user基础信息
        SocialContentIdRO contentIdRO = new SocialContentIdRO(contentIdCO.getId());
//        contentIdRO.setContentType(contentIdCO.getContentType());
        return contentIdRO;
    }
}
