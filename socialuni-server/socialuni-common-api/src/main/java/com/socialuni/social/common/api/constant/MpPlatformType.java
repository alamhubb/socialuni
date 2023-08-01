package com.socialuni.social.common.api.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class MpPlatformType {
    public static final String wx = "weixin";
    public static final String qq = "qq";

    public final static List<String> supportProviderTypes = Arrays.asList(MpPlatformType.wx, MpPlatformType.qq);

    public static boolean isMpQQ(String platform, String mpPlatform) {
        return PlatformType.isMp(platform) && MpPlatformType.qq.equals(mpPlatform);
    }

    public static boolean isMpWx(String platform, String mpPlatform) {
        return PlatformType.isMp(platform) && MpPlatformType.wx.equals(mpPlatform);
    }
}
