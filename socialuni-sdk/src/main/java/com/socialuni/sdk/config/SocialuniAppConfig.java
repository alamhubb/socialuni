package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.config.SocialuniAppType;
import com.socialuni.sdk.utils.ObjectUtil;

public class SocialuniAppConfig {
    public static String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    public static SocialuniAppConfigBO appConfig = SocialuniAppType.getDefaultTypeAppConfig();
    public static SocialuniAppMoreConfigBO appMoreConfig = SocialuniAppType.getDefaultAppMoreConfig();


    public static void resetSocialuniAppConfig(String appType, SocialuniAppConfigBO appConfig, SocialuniAppMoreConfigBO appMoreConfig) {
        if (appType == null) {
            appType = SocialuniAppType.defaultType;
        }
        SocialuniAppConfigBO appTypeConfig = SocialuniAppType.getAppConfigByAppType(appType);
        if (appConfig == null) {
            SocialuniAppConfig.appConfig = appTypeConfig;
        } else {
            //写了appConfig，则完全已自定义的为准
            SocialuniAppConfig.appConfig = ObjectUtil.mergeObjects(appConfig, appTypeConfig);
        }
        if (appMoreConfig != null) {
            SocialuniAppConfig.appMoreConfig = ObjectUtil.mergeObjects(appMoreConfig, SocialuniAppConfig.appMoreConfig);
        }
    }
}
