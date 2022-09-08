package com.socialuni.sdk.factory.user.base;

import com.socialuni.sdk.dao.DO.SocialCommonBaseDO;
import com.socialuni.sdk.model.RO.community.SocialContentIdRO;

public class SocialContentIdROFactory {
    public static SocialContentIdRO getContentIdRO(SocialCommonBaseDO contentIdCO) {
        //user基础信息
        SocialContentIdRO contentIdRO = new SocialContentIdRO(contentIdCO.getId());
//        contentIdRO.setContentType(contentIdCO.getContentType());
        return contentIdRO;
    }
}
