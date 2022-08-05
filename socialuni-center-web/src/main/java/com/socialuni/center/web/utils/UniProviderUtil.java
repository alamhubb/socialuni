package com.socialuni.center.web.utils;

import com.socialuni.center.web.constant.platform.UniappProviderType;
import com.socialuni.center.web.model.UniUnionIdRO;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.exception.UniSdkException;

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
