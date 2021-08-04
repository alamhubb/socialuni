package com.socialuni.sdk.constant;

import com.socialuni.sdk.constant.platform.ProviderType;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class ProviderLoginType extends ProviderType {
    //渠道
    public static final String socialuni = "socialuni";

    public static final List<String> values = Arrays.asList(ProviderLoginType.qq, ProviderLoginType.wx, ProviderLoginType.socialuni);
}
