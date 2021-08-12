package com.socialuni.sdk.constant.platform;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class PlatformType {
    //系统
    public static final String h5 = "h5";
    public static final String app = "app";
    @Deprecated
    public static final String android = "android";
    public static final String mp = "mp";


    public static final String notSupportTypeErrorMsg = "不支持的平台类型";


    public static final List<String> values = Arrays.asList(PlatformType.mp, PlatformType.app, PlatformType.h5);
}
