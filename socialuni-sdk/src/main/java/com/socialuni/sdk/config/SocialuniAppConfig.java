package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.config.SocialuniAppType;

public class SocialuniAppConfig {
    public static String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    public static SocialuniAppConfigBO appConfig = SocialuniAppType.defaultAppConfigBO;
    public static SocialuniAppMoreConfigBO appMoreConfig = new SocialuniAppMoreConfigBO();
}
