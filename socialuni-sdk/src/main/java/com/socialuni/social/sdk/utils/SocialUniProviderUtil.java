package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;

public class SocialUniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();
        if (SocialuniAccountProviderType.socialuni.equals(provider)) {
            return SocialProviderUtil.getSocialUnionIdRO(unionIdData);
        } else if (SocialuniAccountProviderType.openIm.equals(provider)) {
            return SocialProviderUtil.getOpenImUnionIdRO(unionIdData);
        }
        return UniProviderUtil.getUnionIdRO(unionIdData);
    }
}
