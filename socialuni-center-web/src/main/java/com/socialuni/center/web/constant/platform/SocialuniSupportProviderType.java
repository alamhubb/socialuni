package com.socialuni.center.web.constant.platform;

import com.socialuni.social.exception.SocialParamsException;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SocialuniSupportProviderType {
    public static final String wx = UniappProviderType.wx;
    public static final String qq = UniappProviderType.qq;

    public final static List<String> supportProviderTypes = Arrays.asList(wx, qq);

    public static final String notSupportTypeErrorMsg = "社交联盟不支持的授权渠道";

    public static void checkSupportType(String type) {
        if (!supportProviderTypes.contains(type)) {
            throw new SocialParamsException(notSupportTypeErrorMsg);
        }
    }
}
