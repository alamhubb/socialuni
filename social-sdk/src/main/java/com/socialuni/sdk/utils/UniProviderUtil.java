package com.socialuni.sdk.utils;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.constant.platform.ProviderType;
import com.socialuni.sdk.exception.UniSdkException;
import com.socialuni.sdk.model.UniUnionIdRO;

public class UniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();

        if (ProviderType.qq.equals(provider)) {

            return QQProviderUtil.getQQUnionIdRO(unionIdData);

        } else if (ProviderType.wx.equals(provider)) {

            return WxProviderUtil.getWxUnionIdRO(unionIdData);
        }

        throw new UniSdkException(ProviderType.notSupportTypeErrorMsg);
    }
}
