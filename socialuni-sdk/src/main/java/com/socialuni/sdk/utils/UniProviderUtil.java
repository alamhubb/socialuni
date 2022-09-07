package com.socialuni.sdk.utils;

import com.socialuni.sdk.constant.platform.UniappProviderType;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.web.sdk.exception.UniSdkException;

public class UniProviderUtil {
    public static UniUnionIdRO getUnionIdRO(SocialProviderLoginQO unionIdData) {
        String provider = unionIdData.getProvider();

        if (UniappProviderType.qq.equals(provider)) {

            return QQProviderUtil.getQQUnionIdRO(unionIdData);

        } else if (UniappProviderType.wx.equals(provider)) {

            return WxProviderUtil.getWxUnionIdRO(unionIdData);
        }

        throw new UniSdkException(UniappProviderType.notSupportTypeErrorMsg);
    }
}
