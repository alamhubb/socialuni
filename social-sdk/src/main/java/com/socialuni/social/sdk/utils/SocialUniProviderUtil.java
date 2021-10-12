package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.SocialuniProviderLoginType;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;

public class SocialUniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();
        if (SocialuniProviderLoginType.socialuni.equals(provider)) {
            return SocialProviderUtil.getSocialUnionIdRO(unionIdData);
        }
        return UniProviderUtil.getUnionIdRO(unionIdData);
    }
}
