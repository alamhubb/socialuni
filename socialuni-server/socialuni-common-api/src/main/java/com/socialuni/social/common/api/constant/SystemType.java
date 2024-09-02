package com.socialuni.social.common.api.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SystemType {
    public static final String ios = "ios";
    public static final String android = "android";

    public static final String notSupportTypeErrorMsg = "不支持的系统类型";

    public static final List<String> values = Arrays.asList(SystemType.ios, SystemType.android);
}
