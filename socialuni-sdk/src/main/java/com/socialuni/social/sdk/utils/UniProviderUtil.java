package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.platform.UniappProviderType;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.common.exception.exception.UniSdkException;

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
