package com.socialuni.sdk.utils;

import com.socialuni.sdk.constant.SocialuniProviderLoginType;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;

public class SocialUniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();
        if (SocialuniProviderLoginType.socialuni.equals(provider)) {
            return SocialProviderUtil.getSocialUnionIdRO(unionIdData);
        }
        return UniProviderUtil.getUnionIdRO(unionIdData);
    }
}
