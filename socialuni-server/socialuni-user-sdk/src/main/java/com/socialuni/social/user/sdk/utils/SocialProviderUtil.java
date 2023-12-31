package com.socialuni.social.user.sdk.utils;


import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;

public class SocialProviderUtil {
    public static UniUnionIdRO getSocialUnionIdRO(SocialProviderLoginQO loginQO) {
        // 微信需要单独解析
        UniUnionIdRO uniUnionIdRO = new UniUnionIdRO();
        uniUnionIdRO.setOpenid(loginQO.getOpenId());
        uniUnionIdRO.setUnionid(loginQO.getUnionId());
        uniUnionIdRO.setSession_key(loginQO.getCode());
        return uniUnionIdRO;

    }

    public static UniUnionIdRO getOpenImUnionIdRO(SocialProviderLoginQO loginQO) {
        // 微信需要单独解析
        UniUnionIdRO uniUnionIdRO = new UniUnionIdRO();
        uniUnionIdRO.setOpenid(loginQO.getOpenId());
        uniUnionIdRO.setUnionid(loginQO.getUnionId());
        uniUnionIdRO.setSession_key(loginQO.getCode());
        return uniUnionIdRO;

    }
}
