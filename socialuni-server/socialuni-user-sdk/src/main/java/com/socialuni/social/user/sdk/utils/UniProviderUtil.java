package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.content.constant.SocialuniSupportProviderType;
import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.common.api.exception.exception.UniSdkException;

public class UniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();

        if (SocialuniSupportProviderType.qq.equals(provider)) {

            return QQProviderUtil.getQQUnionIdRO(unionIdData);

        } else if (SocialuniSupportProviderType.wx.equals(provider)) {

            return WxProviderUtil.getWxUnionIdRO(unionIdData);
        }

        throw new UniSdkException(SocialuniSupportProviderType.notSupportTypeErrorMsg);
    }
}
