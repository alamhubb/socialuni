package com.socialuni.center.web.utils;


import com.socialuni.center.web.model.UniUnionIdRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;

public class SocialProviderUtil {
    public static UniUnionIdRO getSocialUnionIdRO(SocialProviderLoginQO unionIdData) {
        // 微信需要单独解析
        UniUnionIdRO uniUnionIdRO = new UniUnionIdRO();
        uniUnionIdRO.setOpenid(unionIdData.getOpenId());
        uniUnionIdRO.setUnionid(unionIdData.getUnionId());
        uniUnionIdRO.setSession_key(unionIdData.getCode());
        return uniUnionIdRO;

    }
}
