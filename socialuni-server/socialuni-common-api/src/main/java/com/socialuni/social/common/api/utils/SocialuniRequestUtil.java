package com.socialuni.social.common.api.utils;

import com.qingchi.qing.utils.QingRequestUtil;
import com.socialuni.social.common.api.constant.PlatformType;
import com.qingchi.qing.constant.SocialWebHeaderName;
import com.socialuni.social.common.api.constant.SocialuniSupportProviderType;
import com.socialuni.social.common.api.constant.SystemType;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * 当前线程的request工具类，从request中获取内容
 */
@Slf4j
public class SocialuniRequestUtil {
    public static String getSystem() {
        String system = QingRequestUtil.getRequestValue(SocialWebHeaderName.systemHeaderName);
        if (StringUtils.isEmpty(system)) {
            return null;
        }
        if (SystemType.values.contains(system)) {
            return system;
        }
        throw new SocialParamsException(SystemType.notSupportTypeErrorMsg);
    }

    public static String getProvider() {
        String provider = QingRequestUtil.getRequestValue(SocialWebHeaderName.providerHeaderName);
        if (StringUtils.isEmpty(provider)) {
            return null;
        }
        return SocialuniSupportProviderType.checkSupportType(provider);
    }

    public static String getPlatform() {
        String platform = QingRequestUtil.getRequestValue(SocialWebHeaderName.platformHeaderName);
        if (StringUtils.isEmpty(platform)) {
            return null;
        }
        if (PlatformType.values.contains(platform)) {
            return platform;
        }
        throw new SocialParamsException(PlatformType.notSupportTypeErrorMsg);
    }

    public static String getDeviceUid() {
        String device = QingRequestUtil.getRequestValue(SocialWebHeaderName.deviceUidKey);
        return device;
    }
}
