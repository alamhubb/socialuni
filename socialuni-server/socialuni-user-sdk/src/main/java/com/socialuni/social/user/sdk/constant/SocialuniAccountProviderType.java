package com.socialuni.social.user.sdk.constant;


import com.socialuni.social.common.api.constant.SocialuniSupportProviderType;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SocialuniAccountProviderType extends SocialuniSupportProviderType {
    //渠道
    public static final String socialuni = "socialuni";
    public static final String openIm = "openIm";
    public static final String deviceUid = "deviceUid";

    public static final List<String> values = Arrays.asList(SocialuniAccountProviderType.qq, SocialuniAccountProviderType.wx, SocialuniAccountProviderType.socialuni);
}
