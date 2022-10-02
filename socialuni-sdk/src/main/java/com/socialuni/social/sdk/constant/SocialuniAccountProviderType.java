package com.socialuni.social.sdk.constant;

import com.socialuni.social.sdk.constant.platform.UniappProviderType;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SocialuniAccountProviderType extends UniappProviderType {
    //渠道
    public static final String socialuni = "socialuni";

    public static final List<String> values = Arrays.asList(SocialuniAccountProviderType.qq, SocialuniAccountProviderType.wx, SocialuniAccountProviderType.socialuni);
}