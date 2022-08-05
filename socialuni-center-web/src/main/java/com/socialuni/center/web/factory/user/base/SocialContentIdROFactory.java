package com.socialuni.center.web.factory.user.base;

import com.socialuni.center.web.model.DO.SocialContentIdCO;

public class SocialContentIdROFactory {
    public static SocialContentIdCO getContentIdRO(SocialContentIdCO contentIdCO) {
        //user基础信息
        SocialContentIdCO contentIdRO = new SocialContentIdCO();
        contentIdRO.setId(contentIdCO.getId());
        contentIdRO.setContentType(contentIdCO.getContentType());
        return contentIdRO;
    }
}
