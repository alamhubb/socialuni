package com.socialuni.social.common.api.constant;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SocialuniSupportProviderType {
    public static final String wx = "weixin";
    public static final String qq = "qq";

    public final static List<String> supportProviderTypes = Arrays.asList(wx, qq);

    public static final String notSupportTypeErrorMsg = "社交联盟不支持的授权渠道";

    public static String checkSupportType(String type) {
        if (!supportProviderTypes.contains(type)) {
            throw new SocialParamsException(notSupportTypeErrorMsg);
        }
        return type;
    }
}
