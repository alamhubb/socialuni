package com.socialuni.social.user.sdk.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class UniappProviderType {
    public static final String wx = "weixin";
    public static final String qq = "qq";

    public static final String baidu = "baidu";
    public static final String toutiao = "toutiao";

    public static final String notSupportTypeErrorMsg = "不支持的登录渠道类型";

    public static final List<String> values = Arrays.asList(UniappProviderType.qq, UniappProviderType.wx);
}
