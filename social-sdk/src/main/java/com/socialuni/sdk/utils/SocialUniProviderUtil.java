package com.socialuni.sdk.utils;

import com.socialuni.sdk.constant.ProviderLoginType;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;

public class SocialUniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();
        if (ProviderLoginType.socialuni.equals(provider)) {
            return SocialProviderUtil.getSocialUnionIdRO(unionIdData);
        }
        return UniProviderUtil.getUnionIdRO(unionIdData);
    }
}
