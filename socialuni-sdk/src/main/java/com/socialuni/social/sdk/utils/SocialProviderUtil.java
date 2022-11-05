package com.socialuni.social.sdk.utils;


import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;

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
