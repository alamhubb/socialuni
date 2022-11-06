package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.sdk.constant.platform.QQUrl;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.utils.common.RestUtil;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.common.api.exception.exception.UniSdkException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class QQProviderUtil {
    private static String qqMpId;
    private static String qqMpSecret;

    @Value("${socialuni.provider.qq.qq-mp-id}")
    public void setQqMpId(String qqMpId) {
        QQProviderUtil.qqMpId = qqMpId;
    }

    @Value("${socialuni.provider.qq.qq-mp-secret}")
    public void setQqMpSecret(String qqMpSecret) {
        QQProviderUtil.qqMpSecret = qqMpSecret;
    }

    public static UniUnionIdRO getQQUnionIdRO(SocialProviderLoginQO unionIdData) {
        String platform = RequestUtil.getPlatform();
        String code = unionIdData.getCode();

        String url = QQProviderUtil.getUnionIdUrl(platform, code);
        ResponseEntity<UniUnionIdRO> responseEntity = RestUtil.getXmlRestTemplate().getForEntity(url, UniUnionIdRO.class);
        return responseEntity.getBody();
    }

    public static String getUnionIdUrl(String platform, String code) {
        if (PlatformType.mp.equals(platform)) {
            return MessageFormat.format(QQUrl.qq_mp_unionId_url, qqMpId, qqMpSecret, code);
        } else if (PlatformType.app.equals(platform)) {
            //微信
            return MessageFormat.format(QQUrl.qq_app_unionId_url, code);
        }
        throw new UniSdkException(PlatformType.notSupportTypeErrorMsg + ":" + platform);
    }
}
